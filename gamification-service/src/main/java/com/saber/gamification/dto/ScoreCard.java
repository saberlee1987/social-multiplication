package com.saber.gamification.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "score_cards")
@Data
public class ScoreCard {

    private static final Integer DEFAULT_SCORE = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;
    private Long userId;
    private Long attemptId;
    private Long scoreTimeStamp;
    private Integer score;

    public ScoreCard() {
        this( null, null, 0);
    }

    public ScoreCard( Long userId, Long attemptId, Integer score) {
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimeStamp = System.currentTimeMillis();
        this.score = score;
    }
    public ScoreCard(Long userId, Long attemptId) {
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimeStamp = System.currentTimeMillis();
        this.score = DEFAULT_SCORE;
    }
}
