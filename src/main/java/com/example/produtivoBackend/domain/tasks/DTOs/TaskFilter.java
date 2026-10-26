package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TaskFilter(String title,
                         CategoryTask category,
                         StatusTask statusTask,
                         LocalDate startDate,
                         LocalDate endDate,
                         String field,
                         String period,
                         Boolean recurrent) {


}
