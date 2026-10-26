package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.tasks.entity.Task;

import java.time.LocalDateTime;

public record TaskResponse(long id,
                           String title,
                           String description,
                           Long categoryId,
                           StatusTask statusTask,
                           LocalDateTime createdIn,
                           LocalDateTime expirationDate,
                           Boolean recurrent) {

    public TaskResponse(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getCategory().getId(), task.getStatusTask(), task.getCreatedIn(), task.getExpirationDate(), task.getRecurrent());

    }


}
