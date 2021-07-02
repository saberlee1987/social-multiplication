package com.saber.social.multiplication_service.service.impl;

import com.saber.social.multiplication_service.service.RandomGenerationService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGenerationServiceImpl implements RandomGenerationService {

    @Override
    public Integer generateRandomFactor() {
        Integer MINIMUM_FACTOR = 11;
        Integer MAXIMUM_FACTOR = 99;
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
