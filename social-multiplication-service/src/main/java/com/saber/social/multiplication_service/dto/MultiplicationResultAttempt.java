package com.saber.social.multiplication_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "multiplication_result_attempts")
@NoArgsConstructor
@AllArgsConstructor
public class MultiplicationResultAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "multiplication_id")
	private Multiplication multiplication;
	private Integer resultAttempt;
	private boolean correct;
	
}
