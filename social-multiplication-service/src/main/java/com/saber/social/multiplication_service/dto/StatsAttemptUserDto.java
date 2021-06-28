package com.saber.social.multiplication_service.dto;

import lombok.Data;

import java.util.List;
@Data
public class StatsAttemptUserDto {
    private List<MultiplicationResultAttempt> resultAttempts;
}
