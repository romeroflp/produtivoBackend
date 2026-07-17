package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(UUID userId,
                           String title,
                           String description,
                           CategoryTask category,
                           StatusTask statusTask,
                           LocalDateTime createdIn,
                           LocalDateTime expirationDate,
                           Boolean recurrent) {

    public TaskResponse(Task task) {
        this(task.getUsers().getId(), task.getTitle(), task.getDescription(), task.getCategory(), task.getStatusTask(), task.getCreatedIn(), task.getExpirationDate(), task.getRecurrent());

    }


}
