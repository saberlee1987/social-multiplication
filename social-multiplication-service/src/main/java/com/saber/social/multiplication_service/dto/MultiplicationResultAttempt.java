package com.saber.social.multiplication_service.dto;

import lombok.Data;

@Data
public class MultiplicationResultAttempt {
	private User user;
	private Multiplication multiplication;
	private Integer resultAttempt;
	
}
