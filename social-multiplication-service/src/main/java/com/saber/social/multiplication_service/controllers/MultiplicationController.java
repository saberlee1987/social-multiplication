package com.saber.social.multiplication_service.controllers;

import com.saber.social.multiplication_service.dto.Multiplication;
import com.saber.social.multiplication_service.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/multiplications")
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    public MultiplicationController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping(value = "/random",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Multiplication> createRandomMultiplication(){

        Multiplication multiplication =this.multiplicationService.createRandomMultiplication();

        log.info("Response createRandomMultiplication ===> {}",multiplication);

        return ResponseEntity.ok(multiplication);
    }


}
