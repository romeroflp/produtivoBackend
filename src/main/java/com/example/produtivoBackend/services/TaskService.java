package com.example.produtivoBackend.services;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;
import com.example.produtivoBackend.repository.TaskRepository;
import com.example.produtivoBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService{
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;

    public TaskResponse createTask(TaskRequest taskRequest){

        Users user = userRepo.findById(taskRequest.id())
                .orElseThrow(RuntimeException::new);
        Task task = taskRepo.save(TaskRequest.toEntity(user,taskRequest));

        return TaskResponse.toResponseDTO(task);

    }
}
