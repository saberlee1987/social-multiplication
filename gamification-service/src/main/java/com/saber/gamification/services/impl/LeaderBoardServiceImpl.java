package com.saber.gamification.services.impl;

import com.saber.gamification.dto.LeaderBoardRow;
import com.saber.gamification.repositores.ScoreCardRepository;
import com.saber.gamification.services.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreCardRepository scoreCardRepository;

    public LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    @Transactional
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        LeaderBoardRow leaderBoardRow = this.scoreCardRepository.findFirst10();
        List<LeaderBoardRow> leaderBoardRows = new ArrayList<>();
        leaderBoardRows.add(leaderBoardRow);
        return leaderBoardRows;
    }
}
