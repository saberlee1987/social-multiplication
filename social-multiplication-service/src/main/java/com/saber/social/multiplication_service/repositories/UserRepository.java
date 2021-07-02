package com.saber.social.multiplication_service.repositories;

import com.saber.social.multiplication_service.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAlias(String alias);
}
