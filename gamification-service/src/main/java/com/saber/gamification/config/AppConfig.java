package com.saber.gamification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class AppConfig implements RabbitListenerConfigurer {

    @Value(value = "${spring.rabbitmq.multiplication.exchange}")
    private String multiplicationExchange;

    @Value(value = "${spring.rabbitmq.multiplication.solved.key}")
    private String multiplicationSolvedKey;

    @Value(value = "${spring.rabbitmq.multiplication.queue}")
    private String multiplicationQueue;

    @Bean
    public TopicExchange multiplicationExchange(){
        return new TopicExchange(multiplicationExchange,true,false);
    }

    @Bean
    public Queue multiplicationQueue(){
        return new Queue(multiplicationQueue,true);
    }
    @Bean
    public Binding binding(Queue queue , TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(multiplicationSolvedKey);
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        return new MappingJackson2MessageConverter();
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(messageConverter());
        return messageHandlerMethodFactory;
    }
}
