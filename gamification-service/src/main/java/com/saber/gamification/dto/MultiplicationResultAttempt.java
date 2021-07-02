package com.saber.gamification.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.saber.gamification.util.MultiplicationResultAttemptDeserialize;

import java.io.Serializable;
import java.util.Objects;

@JsonDeserialize(using = MultiplicationResultAttemptDeserialize.class)
public class MultiplicationResultAttempt implements Serializable {
    private Integer resultAttempt;
    private Integer factorA;
    private Integer factorB;
    private String userAlias;
    private boolean correct;

    public MultiplicationResultAttempt() {
    }

    public MultiplicationResultAttempt(Integer resultAttempt, Integer factorA, Integer factorB, String userAlias, boolean correct) {
        this.resultAttempt = resultAttempt;
        this.factorA = factorA;
        this.factorB = factorB;
        this.userAlias = userAlias;
        this.correct = correct;
    }

    public Integer getResultAttempt() {
        return resultAttempt;
    }

    public void setResultAttempt(Integer resultAttempt) {
        this.resultAttempt = resultAttempt;
    }

    public Integer getFactorA() {
        return factorA;
    }

    public void setFactorA(Integer factorA) {
        this.factorA = factorA;
    }

    public Integer getFactorB() {
        return factorB;
    }

    public void setFactorB(Integer factorB) {
        this.factorB = factorB;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiplicationResultAttempt that = (MultiplicationResultAttempt) o;
        return correct == that.correct &&
                Objects.equals(resultAttempt, that.resultAttempt) &&
                Objects.equals(factorA, that.factorA) &&
                Objects.equals(factorB, that.factorB) &&
                Objects.equals(userAlias, that.userAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultAttempt, factorA, factorB, userAlias, correct);
    }

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
                "resultAttempt=" + resultAttempt +
                ", factorA=" + factorA +
                ", factorB=" + factorB +
                ", userAlias='" + userAlias + '\'' +
                ", correct=" + correct +
                '}';
    }
}
