package com.saber.social.multiplication_service.repositories;

import com.saber.social.multiplication_service.dto.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiplicationRepository extends JpaRepository<Multiplication, Long> {

}
