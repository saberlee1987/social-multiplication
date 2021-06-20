package com.saber.social.multiplication_service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.User;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import com.saber.social.multiplication_service.service.impl.MultiplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CheckCorrectAttemptTest {
	@Mock
	private RandomGenerationService randomGenerationService;
	private MultiplicationService multiplicationService;
	
	@BeforeEach
	void setup(){
		MockitoAnnotations.openMocks(this);
		this.multiplicationService= new MultiplicationServiceImpl(randomGenerationService);
	}
	@Test
	void checkCorrectAttemptTest(){
		
		Multiplication multiplication= new Multiplication();
		multiplication.setFactorA(50);
		multiplication.setFactorB(60);
		
		User user= new User();
		user.setAlias("saber");
		
		
		MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt();
		resultAttempt.setMultiplication(multiplication);
		resultAttempt.setUser(user);
		resultAttempt.setResultAttempt(3000);
		
		boolean attemptResult= multiplicationService.checkAttempt(resultAttempt);
		
		Assertions.assertThat(attemptResult).isTrue();
		
		
	}
	
	
	@Test
	void checkWrongAttemptTest(){
		
		Multiplication multiplication= new Multiplication();
		multiplication.setFactorA(50);
		multiplication.setFactorB(60);
		
		User user= new User();
		user.setAlias("saber");
		
		
		MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt();
		resultAttempt.setMultiplication(multiplication);
		resultAttempt.setUser(user);
		resultAttempt.setResultAttempt(3600);
		
		boolean attemptResult= multiplicationService.checkAttempt(resultAttempt);
		
		Assertions.assertThat(attemptResult).isFalse();
		
		
	}
}
