package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.CategoryTask;
import com.example.produtivoBackend.domain.tasks.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequest(UUID userId,
                          @NotBlank
                          String title,
                          String description,
                          @NotNull
                          CategoryTask category,
                          @NotNull
                          StatusTask statusTask,
                          LocalDateTime expirationDate,
                          boolean recurrent) {



}
