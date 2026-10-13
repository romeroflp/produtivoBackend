package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;

public record TaskRequest(@NotBlank
                          String title,
                          String description,
                          @NotNull
                          Long categoryId,
                          StatusTask statusTask,
                          LocalDateTime expirationDate,
                          Boolean recurrent) {



}
