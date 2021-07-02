package com.saber.social.multiplication_service.service.impl;

import com.saber.event.dto.MultiplicationSolvedEvent;
import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.User;
import com.saber.social.multiplication_service.event.handler.EventHandler;
import com.saber.social.multiplication_service.repositories.MultiplicationResultAttemptRepository;
import com.saber.social.multiplication_service.repositories.UserRepository;
import com.saber.social.multiplication_service.service.MultiplicationService;
import com.saber.social.multiplication_service.service.RandomGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGenerationService randomGenerationService;

    private MultiplicationResultAttemptRepository resultAttemptRepository;

    private UserRepository userRepository;

    private EventHandler eventHandler;

    @Autowired
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public MultiplicationServiceImpl(RandomGenerationService randomGenerationService, MultiplicationResultAttemptRepository resultAttemptRepository, UserRepository userRepository) {
        this.randomGenerationService = randomGenerationService;
        this.resultAttemptRepository = resultAttemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Multiplication createRandomMultiplication() {
        Integer factorA = randomGenerationService.generateRandomFactor();
        Integer factorB = randomGenerationService.generateRandomFactor();
        Integer result = factorA * factorB;
        log.info("result multiplication {} * {} = {}", factorA, factorB, result);
        return new Multiplication(factorA, factorB);

    }

    @Override
    @Transactional
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        log.info("checkAttempt Request ===> {}", resultAttempt);

        Optional<User> user = this.userRepository.findByAlias(resultAttempt.getUser().getAlias());


        User userAttempt = user.orElse(resultAttempt.getUser());

        Multiplication multiplication = resultAttempt.getMultiplication();
        if (multiplication == null)
            return false;

        Integer userResult = multiplication.getFactorA() * multiplication.getFactorB();

        if (resultAttempt.getResultAttempt() == null)
            return false;

        boolean correct = resultAttempt.getResultAttempt().equals(userResult);

        MultiplicationResultAttempt checkedAttempt = MultiplicationResultAttempt.builder()
                .multiplication(multiplication)
                .user(userAttempt)
                .resultAttempt(resultAttempt.getResultAttempt())
                .correct(correct)
                .build();

        this.resultAttemptRepository.save(checkedAttempt);

        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent();
        event.setCorrect(correct);
        event.setMultiplicationResultAttemptId(checkedAttempt.getId());
        event.setUserId(checkedAttempt.getUser().getId());

        this.eventHandler.sendMultiplicationSolvedEvent(event);

        log.info("checkedAttempt ===> {}", checkedAttempt);
        return resultAttempt.getResultAttempt().equals(userResult);
    }

    @Override
    @Transactional
    public List<MultiplicationResultAttempt> getStatsForUser(String alias) {
        return this.resultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(alias);
    }
}
