package com.saber.gamification.repositores;

import com.saber.gamification.dto.LeaderBoardRow;
import com.saber.gamification.dto.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends JpaRepository<ScoreCard,Long> {

    @Query(value = "SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId=:userId GROUP BY s.userId")
    Integer getTotalScoreCard(@Param("userId") Long userId);

    @Query(value = "SELECT NEW com.saber.gamification.dto.LeaderBoardRow(s.userId,SUM(s.score)) FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC ")
    LeaderBoardRow findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimeStampDesc(Long userId);
}
