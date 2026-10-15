package com.example.produtivoBackend.domain.tasks.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskSelectAllDataDTO(UUID userId,
                                   String title,
                                   String description,
                                   LocalDateTime createdId,
                                   LocalDateTime expirationDate,
                                   Long id,
                                   String name,
                                   String StatusTask,
                                   boolean recurrent,
                                   String colorIcon) {
    public TaskSelectAllDataDTO(UUID userId, String title, String description, LocalDateTime createdId, LocalDateTime expirationDate, Long id, String name, String StatusTask, boolean recurrent, String colorIcon) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdId = createdId;
        this.expirationDate = expirationDate;
        this.id = id;
        this.name = name;
        this.StatusTask = StatusTask;
        this.recurrent = recurrent;
        this.colorIcon = colorIcon;
    }
}
