package com.example.produtivoBackend.domain.tasks;

import com.example.produtivoBackend.domain.users.Users;

import java.time.LocalDateTime;
import java.util.UUID;



public record TaskDTO(String title,
                      UUID user_id,
                      String description,
                      String category,
                      String status_task,
                      LocalDateTime created_in,
                      LocalDateTime expiration_date,
                      boolean recurrent){

//    public static Task toEntity(TaskDTO dto, Users user, CategoryTask categoryTask, StatusTask statusTask){
//        return new Task(
//                user,
//                dto.title(),
//                dto.description(),
//                categoryTask,
//                statusTask,
//                dto.expiration_date(),
//                dto.created_in,
//                dto.recurrent()
//
//
//        );
//    }

}
