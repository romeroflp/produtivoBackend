package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequest(
                          @NotBlank
                          String title,
                          String description,
                          @NotNull
                          CategoryTask category,
                          StatusTask statusTask,
                          LocalDateTime expirationDate,
                          Boolean recurrent) {



}
