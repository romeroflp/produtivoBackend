package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.tasks.entity.Task;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(long id,
                           String title,
                           String description,
                           Long category,
                           StatusTask statusTask,
                           LocalDateTime createdIn,
                           LocalDateTime expirationDate,
                           Boolean recurrent) {

    public TaskResponse(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getCategory().getId(), task.getStatusTask(), task.getCreatedIn(), task.getExpirationDate(), task.getRecurrent());

    }


}
