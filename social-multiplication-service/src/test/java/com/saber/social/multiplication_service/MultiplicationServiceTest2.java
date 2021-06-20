package com.saber.social.multiplication_service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import com.saber.social.multiplication_service.service.impl.MultiplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MultiplicationServiceTest2 {
	
	private MultiplicationService multiplicationService;
	
	@Mock
	private RandomGenerationService randomGenerationService;
	
	@BeforeEach
	void setup(){
		
		MockitoAnnotations.openMocks(this);
	this.multiplicationService=
			new MultiplicationServiceImpl(randomGenerationService);
	}
	
	@Test
	void createMultiplicationTest(){
		Mockito.when(randomGenerationService.generateRandomFactor())
				.thenReturn(50,30);
		
		Multiplication multiplication =multiplicationService.createRandomMultiplication();
		int result= multiplication.getFactorA() * multiplication.getFactorB();
		Assertions.assertThat(multiplication.getFactorA()).isEqualTo(50);
		Assertions.assertThat(multiplication.getFactorB()).isEqualTo(30);
		Assertions.assertThat(result).isEqualTo(1500);
	
	
	}
	
}
