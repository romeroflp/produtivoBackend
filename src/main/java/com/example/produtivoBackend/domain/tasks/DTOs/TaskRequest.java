package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;

public record TaskRequest(Long id,
                          @NotBlank
                          String title,
                          String description,
                          @NotNull
                          Long categoryId,
                          StatusTask statusTask,
                          LocalDateTime expirationDate,
                          Boolean recurrent) {

    public TaskRequest(Long id, @NotBlank
    String title, String description, @NotNull Long categoryId, StatusTask statusTask, LocalDateTime expirationDate, Boolean recurrent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.statusTask = statusTask;
        this.expirationDate = expirationDate;
        this.recurrent = recurrent;
    }

    public TaskRequest(@NotBlank
    String title, String description, @NotNull Long categoryId, StatusTask statusTask, LocalDateTime expirationDate, Boolean recurrent) {
        this(null, title, description, categoryId, statusTask, expirationDate, recurrent);
    }
}
