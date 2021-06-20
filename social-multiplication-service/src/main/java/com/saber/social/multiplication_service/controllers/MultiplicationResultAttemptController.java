package com.saber.social.multiplication_service.controllers;

import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.ResultResponse;
import com.saber.social.multiplication_service.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/results")
@Slf4j
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt){

        log.info("Request for postResult ==> {}",multiplicationResultAttempt);

        boolean isCorrect =this.multiplicationService.checkAttempt(multiplicationResultAttempt);

        log.info("Response postResult ===> {}",isCorrect);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCorrect(isCorrect);
        return ResponseEntity.ok(resultResponse);
    }
}