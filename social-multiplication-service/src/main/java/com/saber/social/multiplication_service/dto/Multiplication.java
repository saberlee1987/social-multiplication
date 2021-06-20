package com.saber.social.multiplication_service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Multiplication implements Serializable {
	private Integer factorA;
	private Integer factorB;
	//private Integer result;
	
	public Multiplication() {
	}
	
	public Multiplication(Integer factorA, Integer factorB) {
		this.factorA = factorA;
		this.factorB = factorB;
		//this.result=factorA*factorB;
	}
}
