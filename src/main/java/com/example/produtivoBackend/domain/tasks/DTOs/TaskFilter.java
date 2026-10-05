package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskFilter(String title,
                         CategoryTask category,
                         StatusTask statusTask,
                         LocalDate expirationDate,
                         LocalTime expirationTime,
                         Boolean recurrent) {


}
