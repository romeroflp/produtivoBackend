package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.users.entities.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, UUID> {
    ResetToken findByToken(UUID token);
}
