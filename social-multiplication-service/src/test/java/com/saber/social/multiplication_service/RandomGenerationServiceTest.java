package com.saber.social.multiplication_service;

import com.saber.social.multiplication_service.service.RandomGenerationService;
import com.saber.social.multiplication_service.service.impl.RandomGenerationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@SpringBootTest
public class RandomGenerationServiceTest {
	
//	@Autowired
	private RandomGenerationService randomGenerationService;
	
	@BeforeEach
	public void beforeTest(){
		randomGenerationService = new RandomGenerationServiceImpl();
	}
	
	@Test
	void generationRandomFactorIsBetweenExpectedLimits(){
		
		List<Integer> randomFactors = IntStream.range(0,1000).
				map(i->randomGenerationService.generateRandomFactor()).boxed()
				.collect(Collectors.toList());
		
		Assertions.assertThat(randomFactors).
				containsAnyElementsOf(IntStream.range(11,100)
						.boxed().collect(Collectors.toList())
				);
	}
}
