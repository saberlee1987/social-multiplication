package com.saber.social.multiplication_service;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.User;
import com.saber.social.multiplication_service.repositories.MultiplicationResultAttemptRepository;
import com.saber.social.multiplication_service.repositories.UserRepository;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import com.saber.social.multiplication_service.service.impl.MultiplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class CheckCorrectAttemptTest {
    @Mock
    private RandomGenerationService randomGenerationService;
    private MultiplicationService multiplicationService;

    @Mock
    private MultiplicationResultAttemptRepository resultAttemptRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.multiplicationService = new MultiplicationServiceImpl(randomGenerationService,
                resultAttemptRepository, userRepository);
    }

    @Test
    void checkCorrectAttemptTest() {

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorA(50);
        multiplication.setFactorB(60);

        User user = new User();
        user.setAlias("saber");


        MultiplicationResultAttempt resultAttempt = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(user)
                .resultAttempt(300)
                .correct(false)
                .build();

        MultiplicationResultAttempt verifiedAttempt = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(user)
                .resultAttempt(300)
                .correct(true)
                .build();

        Mockito.when(userRepository.findByAlias("saber")).thenReturn(Optional.empty());

        boolean attemptResult = multiplicationService.checkAttempt(resultAttempt);

        Assertions.assertThat(attemptResult).isTrue();

        Mockito.verify(resultAttemptRepository).save(verifiedAttempt);


    }


    @Test
    void checkWrongAttemptTest() {

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorA(50);
        multiplication.setFactorB(60);

        User user = new User();
        user.setAlias("saber");


        MultiplicationResultAttempt resultAttempt = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(user)
                .correct(false)
                .resultAttempt(300)
                .build();


        Mockito.when(userRepository.findByAlias("saber")).thenReturn(Optional.empty());

        boolean attemptResult = multiplicationService.checkAttempt(resultAttempt);

        Assertions.assertThat(attemptResult).isFalse();

        Mockito.verify(resultAttemptRepository).save(resultAttempt);

    }

    @Test
    public void retrievedStatsTest() {

        Multiplication multiplication = new Multiplication();
        multiplication.setFactorA(50);
        multiplication.setFactorB(60);

        User user = new User();
        user.setAlias("saber");

        MultiplicationResultAttempt attempt1 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .resultAttempt(3010)
                .correct(false)
                .build();

        MultiplicationResultAttempt attempt2 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .resultAttempt(3030)
                .correct(false)
                .build();


        MultiplicationResultAttempt attempt3 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .resultAttempt(3040)
                .correct(false)
                .build();


        MultiplicationResultAttempt attempt4 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .resultAttempt(3060)
                .correct(false)
                .build();

        MultiplicationResultAttempt attempt5 = MultiplicationResultAttempt.builder()
                .user(user)
                .multiplication(multiplication)
                .resultAttempt(3000)
                .correct(true)
                .build();

        List<MultiplicationResultAttempt> latestAttempt = Lists.list(attempt1, attempt2, attempt3, attempt4, attempt5);

        Mockito.when(resultAttemptRepository.findTop5ByUserAliasOrderByIdDesc("saber"))
                .thenReturn(latestAttempt);

        List<MultiplicationResultAttempt> latestAttemptResult = this.multiplicationService
                .getStatsForUser("saber");

        Assertions.assertThat(latestAttemptResult).isEqualTo(latestAttempt);


    }
}
