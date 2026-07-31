package com.example.produtivoBackend.repository;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SpecificationTask {

    public static Specification<Task> hasTitle(String title){
        return (root, query, cb) ->
                cb.like(root.get("title"), "%"+title+"%");
    }

    public static Specification<Task> hasCategory(CategoryTask categoryTask){
        return (root, query, cb) ->
            cb.equal(root.get("category"), categoryTask);
    }

    public static Specification<Task> hasStatus(StatusTask statusTask){
        return (root, query, cb) ->
            cb.equal(root.get("statusTask"), statusTask);
    }

    public static Specification<Task> hasRecurrent(boolean recurrent){
        return (root, query, cb) -> cb.equal(root.get("recurrent"), recurrent);
    }

    public static Specification<Task> hasTaskInDate(LocalDate date){
        return (root, query, cb) -> {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            return cb.between(root.get("expirationDate"), startOfDay, endOfDay);
        };
    }

    public static Specification<Task> hasTaskInDateTime(LocalDate date, LocalTime time){
        return (root, query, cb) -> {

            LocalDateTime dateTime = LocalDateTime.of(date,time);
            return cb.equal(root.get("expirationDate"), dateTime);
        };
    }



}

// Fazer teste: enviar um DTO filter ao inves de um request