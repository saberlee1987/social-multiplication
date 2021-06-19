package com.saber.social.multiplication_service.service.impl;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MultiplicationServiceImpl implements MultiplicationService {
	
	private RandomGenerationService randomGenerationService;
	
	public MultiplicationServiceImpl(RandomGenerationService randomGenerationService) {
		this.randomGenerationService = randomGenerationService;
	}
	
	@Override
	public Multiplication createRandomMultiplication() {
		Integer factorA = randomGenerationService.generateRandomFactor();
		Integer factorB = randomGenerationService.generateRandomFactor();
		Integer result = factorA*factorB;
	    log.info("result multiplication {} * {} = {}",factorA,factorB,result);
		return new Multiplication(factorA,factorB);
		
	}
}
