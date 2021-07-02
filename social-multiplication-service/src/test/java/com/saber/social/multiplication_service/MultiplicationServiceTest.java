package com.saber.social.multiplication_service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MultiplicationServiceTest {
    @MockBean
    private RandomGenerationService randomGenerationService;
    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplicationTest() {
        Mockito.when(randomGenerationService.generateRandomFactor())
                .thenReturn(50, 30);
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        int result = multiplication.getFactorA() * multiplication.getFactorB();
        Assertions.assertEquals(multiplication.getFactorA(), 50);
        Assertions.assertEquals(multiplication.getFactorB(), 30);
        Assertions.assertEquals(result, 1500);
    }
}
