package com.saber.social.multiplication_service.event.handler;

import com.saber.event.dto.MultiplicationSolvedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.multiplication.exchange}")
    private String multiplicationExchange;

    @Value(value = "${spring.rabbitmq.multiplication.solved.key}")
    private String multiplicationRoutingKey;

    public EventHandler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMultiplicationSolvedEvent(MultiplicationSolvedEvent event) {
        this.rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationRoutingKey, event);
    }
}
