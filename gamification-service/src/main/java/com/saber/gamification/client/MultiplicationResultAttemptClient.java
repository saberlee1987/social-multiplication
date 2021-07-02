package com.saber.gamification.client;

import com.saber.gamification.dto.MultiplicationResultAttempt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "multiplication-service")
//@RibbonClient(value = "multiplication-service")
public interface MultiplicationResultAttemptClient {

    @RequestMapping(value = "/results/{resultAttemptId}")
    ResponseEntity<MultiplicationResultAttempt> findById(@PathVariable(name = "resultAttemptId") Long resultAttemptId);
}
