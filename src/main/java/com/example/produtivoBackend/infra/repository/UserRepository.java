package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID>, PagingAndSortingRepository<Users, UUID> {
    Users findByEmail(String email);
    Optional<Users> findById(UUID uuid);


}
