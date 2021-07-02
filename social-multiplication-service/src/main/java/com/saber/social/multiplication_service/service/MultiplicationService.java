package com.saber.social.multiplication_service.service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {

    Multiplication createRandomMultiplication();

    boolean checkAttempt(MultiplicationResultAttempt resultAttempt);

    List<MultiplicationResultAttempt> getStatsForUser(String alias);

    MultiplicationResultAttempt findById(Long id);
}
