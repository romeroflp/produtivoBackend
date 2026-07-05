package com.example.produtivoBackend.repository;

import com.example.produtivoBackend.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
}
