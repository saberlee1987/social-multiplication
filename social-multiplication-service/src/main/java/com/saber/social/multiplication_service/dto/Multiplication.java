package com.saber.social.multiplication_service.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "multiplications")
public class Multiplication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiplication_id")
    private Long id;
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
