package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.CategoryTask;
import com.example.produtivoBackend.domain.tasks.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(UUID userId,
                           String title,
                           String description,
                           CategoryTask category,
                           StatusTask status_task,
                           LocalDateTime created_in,
                           LocalDateTime expiration_date,
                           boolean recurrent) {

    public TaskResponse(Task task) {
        this(task.getUsers().getId(), task.getTitle(), task.getDescription(), task.getCategory(), task.getStatusTask(), task.getCreated_in(), task.getExpiration_date(), task.isRecurrent());

    }


}
