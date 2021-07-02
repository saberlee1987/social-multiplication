package com.saber.event.dto;

import java.io.Serializable;

public class MultiplicationSolvedEvent implements Serializable {
    private Long multiplicationResultAttemptId;
    private Long userId;
    private Boolean correct;

    public MultiplicationSolvedEvent() {
    }

    public MultiplicationSolvedEvent(Long multiplicationResultAttemptId, Long userId, Boolean correct) {
        this.multiplicationResultAttemptId = multiplicationResultAttemptId;
        this.userId = userId;
        this.correct = correct;
    }

    public Long getMultiplicationResultAttemptId() {
        return multiplicationResultAttemptId;
    }

    public void setMultiplicationResultAttemptId(Long multiplicationResultAttemptId) {
        this.multiplicationResultAttemptId = multiplicationResultAttemptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "MultiplicationSolvedEvent{" +
                "multiplicationResultAttemptId=" + multiplicationResultAttemptId +
                ", userId=" + userId +
                ", correct=" + correct +
                '}';
    }
}
