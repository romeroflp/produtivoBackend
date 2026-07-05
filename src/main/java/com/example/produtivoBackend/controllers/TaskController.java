package com.example.produtivoBackend.controllers;


import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController{
    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest requestTask){
        TaskResponse response = taskService.createTask(requestTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
