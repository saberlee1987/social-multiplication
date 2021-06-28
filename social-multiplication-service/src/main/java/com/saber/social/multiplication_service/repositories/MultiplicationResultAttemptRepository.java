package com.saber.social.multiplication_service.repositories;

import com.saber.social.multiplication_service.dto.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt,Long> {

    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String alias);
}
