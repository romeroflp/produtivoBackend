package com.example.produtivoBackend.domain.tasks.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskSelectAllDataDTO(Long taskId,
                                   UUID userId,
                                   String title,
                                   String description,
                                   LocalDateTime createdIn,
                                   LocalDateTime expirationDate,
                                   Long categoryId,
                                   String name,
                                   String statusTask,
                                   boolean recurrent,
                                   String colorIcon) {

}
