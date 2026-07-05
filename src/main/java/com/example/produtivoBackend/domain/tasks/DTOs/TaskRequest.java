package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.CategoryTask;
import com.example.produtivoBackend.domain.tasks.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequest(UUID id,
                          @NotBlank
                          String title,
                          String description,
                          @NotNull
                          CategoryTask category,
                          @NotNull
                          StatusTask status_task,
                          LocalDateTime expiration_date,
                          boolean recurrent) {

    public static Task toEntity(Users user, TaskRequest request){
        return new Task(
            user,
            request.title(),
            request.description(),
            request.category(),
            request.status_task(),
            request.expiration_date(),
            request.recurrent()
        );
    }
}
