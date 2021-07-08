package com.saber.social.multiplication_service.controllers;

import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import com.saber.social.multiplication_service.dto.StatsAttemptUserDto;
import com.saber.social.multiplication_service.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/results")
@Slf4j
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {

        log.info("Request for postResult ==> {}", multiplicationResultAttempt);

        MultiplicationResultAttempt responseResultAttempt = this.multiplicationService.checkAttempt(multiplicationResultAttempt);

        log.info("Response postResult isCorrect ===> {}", responseResultAttempt.isCorrect());
        log.info("Response postResult ===> {}", responseResultAttempt);

        return ResponseEntity.ok(responseResultAttempt);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<StatsAttemptUserDto> getStatistics(@RequestParam(name = "alias") String alias) {
        List<MultiplicationResultAttempt> resultAttempts = this.multiplicationService.getStatsForUser(alias);
        StatsAttemptUserDto statsAttemptUserDto = new StatsAttemptUserDto();
        statsAttemptUserDto.setResultAttempts(resultAttempts);

        log.info("getStatistics for user {} ===> {}", alias, statsAttemptUserDto);
        return ResponseEntity.ok(statsAttemptUserDto);
    }

    @GetMapping(value = "/{resultAttemptId}")
    public ResponseEntity<MultiplicationResultAttempt> findById(@PathVariable(value = "resultAttemptId") Long resultAttemptId) {
        MultiplicationResultAttempt resultAttempt = this.multiplicationService.findById(resultAttemptId);
        return ResponseEntity.ok(resultAttempt);
    }


}