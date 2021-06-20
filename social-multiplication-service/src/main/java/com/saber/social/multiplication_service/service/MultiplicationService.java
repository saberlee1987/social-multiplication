package com.saber.social.multiplication_service.service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;

public interface MultiplicationService {
	
	Multiplication createRandomMultiplication();
	boolean checkAttempt(MultiplicationResultAttempt resultAttempt);
}
