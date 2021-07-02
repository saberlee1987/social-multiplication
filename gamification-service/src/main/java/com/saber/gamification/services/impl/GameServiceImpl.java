package com.saber.gamification.services.impl;

import com.saber.gamification.dto.Badge;
import com.saber.gamification.dto.BadgeCard;
import com.saber.gamification.dto.GameStats;
import com.saber.gamification.dto.ScoreCard;
import com.saber.gamification.repositores.BadgeCardRepository;
import com.saber.gamification.repositores.ScoreCardRepository;
import com.saber.gamification.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }

    @Override
    @Transactional
    public void newAttemptForUser(Long userId, Long attemptId, boolean correct) {

        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
            log.info("User With id {} scored {} points for attempt id {}",
                    userId, scoreCard.getScore(), attemptId);
            processForBadges(userId, attemptId);
        }

    }

    private void processForBadges(Long userId, Long attemptId) {

        Integer totalScore = scoreCardRepository.getTotalScoreCard(userId);
        log.info("New Score for User {} is {}", userId, totalScore);

        List<ScoreCard> scoreCards = this.scoreCardRepository.findByUserIdOrderByScoreTimeStampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimeStampDesc(userId);


        checkAndGiveBadgeCardOnScore(badgeCardList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId);

        checkAndGiveBadgeCardOnScore(badgeCardList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, userId);

        checkAndGiveBadgeCardOnScore(badgeCardList, Badge.GOLD_MULTIPLICATOR, totalScore, 1000, userId);

        if (scoreCards.size() == 1 && !containsBadge(badgeCardList, Badge.FIRST_WON)) {
            persistBadgeCard(Badge.FIRST_WON, userId);

        }


    }

    private void checkAndGiveBadgeCardOnScore(List<BadgeCard> badgeCards, Badge badge,
                                                             Integer score, Integer scoreThreshold,
                                                             Long userId) {
        if (score >= scoreThreshold && !containsBadge(badgeCards, badge)) {
            persistBadgeCard(badge, userId);
        }

    }

    private boolean containsBadge(List<BadgeCard> badgeCards, Badge badge) {
        return badgeCards.stream().anyMatch(b -> b.getBadge().equals(badge));
    }

    private void persistBadgeCard(Badge badge, Long userId) {
        BadgeCard badgeCard = new BadgeCard();
        badgeCard.setBadge(badge);
        badgeCard.setUserId(userId);
        badgeCard.setBadgeTimeStamp(System.currentTimeMillis());
        this.badgeCardRepository.save(badgeCard);
        log.info("User with id {} won a new badge : {} ", userId, badge);

    }

    @Override
    @Transactional
    public GameStats retrieveStatsForUser(Long userId) {
        Integer score = scoreCardRepository.getTotalScoreCard(userId);
        if (score == null) {
            return new GameStats(userId, 0, Collections.emptyList());
        }
        List<BadgeCard> badgeCards = badgeCardRepository
                .findByUserIdOrderByBadgeTimeStampDesc(userId);

        return new GameStats(userId, score, badgeCards.stream()
                .map(BadgeCard::getBadge).collect(Collectors.toList()));

    }
}
