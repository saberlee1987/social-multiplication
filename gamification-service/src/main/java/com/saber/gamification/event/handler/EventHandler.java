package com.saber.gamification.event.handler;

import com.saber.event.dto.MultiplicationSolvedEvent;
import com.saber.gamification.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {

    private GameService gameService;

    public EventHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.multiplication.queue}")
    public void handleMultiplicationSolvedEvent(MultiplicationSolvedEvent event){
        log.info("MultiplicationSolved Event Received ===> {}",event);
        log.info("MultiplicationSolved Event AttemptId Received ===> {}",event.getMultiplicationResultAttemptId());
        try {
         gameService.newAttemptForUser(event.getUserId(),event.getMultiplicationResultAttemptId(),event.getCorrect());

        }catch (Exception ex){
            log.error("Error when trying to process MultiplicationSolvedEvent ",ex);
        }

    }
}
