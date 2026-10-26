package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.tasks.enums.CategoryTask;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SpecificationTaskRepository {

    public static Specification<Task> hasTitle(String title){
        return (root, query, cb) ->
                cb.like(root.get("title"), "%"+title+"%");
    }

    public static Specification<Task> hasStatus(StatusTask statusTask){
        return (root, query, cb) ->
            cb.equal(root.get("statusTask"), statusTask);
    }

    public static Specification<Task> hasRecurrent(boolean recurrent){
        return (root, query, cb) -> cb.equal(root.get("recurrent"), recurrent);
    }

// Consulta Tarefas pelo dia
    public static Specification<Task> hasTaskOnDate(LocalDate date){
        LocalDateTime startDay = date.atStartOfDay();
        LocalDateTime endDay = date.plusDays(1).atStartOfDay();

        return (root, query, cb) ->
                cb.and(cb.greaterThanOrEqualTo(root.get("createdIn"), startDay),
                        cb.lessThan(root.get("createdIn"), endDay));
    }

    public static Specification<Task> hasTaskOnPeriod(LocalDate startDay, LocalDate endDay){
        LocalDateTime localDateTimeStart = startDay.atStartOfDay();
        LocalDateTime localDateTimeEnd = endDay.plusDays(1).atStartOfDay();

        return (root, query, cb) ->
            cb.and(cb.greaterThanOrEqualTo(root.get("createdIn"), localDateTimeStart),
                    cb.lessThan(root.get("createdIn"), localDateTimeEnd));
    }

    //Consulta tarefas expiradas no dia
    public static Specification<Task> hasTaskExpiredOnDate(LocalDate startDay ){
        LocalDateTime dateTimeStart = startDay.atStartOfDay();
        LocalDateTime dateTimeEnd = startDay.plusDays(1).atStartOfDay();

        return (root, query, cb) ->
                cb.and(
                        cb.greaterThanOrEqualTo(root.get("expirationDate"), dateTimeStart),
                        cb.lessThan(root.get("expirationDate"), dateTimeEnd)
                );
    }


}



