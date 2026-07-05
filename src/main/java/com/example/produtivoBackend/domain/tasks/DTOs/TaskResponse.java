package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.CategoryTask;
import com.example.produtivoBackend.domain.tasks.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(UUID user_id,
                           String title,
                           String description,
                           CategoryTask category,
                           StatusTask status_task,
                           LocalDateTime created_in,
                           LocalDateTime expiration_date,
                           boolean recurrent) {

    public static TaskResponse toResponseDTO(Task task){
        return new TaskResponse(
                task.getUsers().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCategory(),
                task.getStatus_task(),
                task.getCreated_in(),
                task.getExpiration_date(),
                task.isRecurrent());

    }

}
