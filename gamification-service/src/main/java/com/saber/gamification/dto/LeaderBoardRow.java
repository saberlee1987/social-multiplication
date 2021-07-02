package com.saber.gamification.dto;

import lombok.Data;

@Data
public class LeaderBoardRow {
    private Long userId;
    private Long totalScore;

    public LeaderBoardRow() {
    }

    public LeaderBoardRow(Long userId, Long totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
    }
}
