package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.tasks.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUsersId(UUID userId);
}
