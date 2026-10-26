package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.hibernate.engine.transaction.internal.jta.JtaStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController{

    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest taskRequest, Authentication token){
        TaskResponse response = taskService.createTask(taskRequest, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<TaskResponse> searchById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.searchTaskById(id));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<TaskResponse>> searchTask(@PathVariable String title, Authentication auth){
        return ResponseEntity.ok(taskService.searchTaskByTitle(title, auth));
    }

    @GetMapping("/myTasks")
    public ResponseEntity<Page<List<TaskSelectAllDataDTO>>> myTasks(Pageable page, Authentication auth){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.myTasks(page, auth));
    }

    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long idTask, Authentication auth){
        taskService.deleteTask(idTask, auth);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody @Valid TaskRequest request, Authentication auth){
        return ResponseEntity.ok(taskService.updateTask(request, auth));
    }

    @GetMapping("/fillTasks")
    public ResponseEntity<List<TaskResponse>> searchFilterTask(TaskFilter filter, Authentication auth){
        return ResponseEntity.ok(taskService.filterTask(filter, auth));

    }
}
