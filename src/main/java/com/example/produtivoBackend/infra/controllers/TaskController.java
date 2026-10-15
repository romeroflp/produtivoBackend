package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import com.example.produtivoBackend.domain.tasks.services.TaskService;
import com.example.produtivoBackend.domain.users.entities.Users;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{identifier}")
    public ResponseEntity<TaskResponse> searchTask (@PathVariable String identifier){
        return ResponseEntity.ok(taskService.searchTask(identifier));
    }

    @GetMapping("/allTasks")
    public ResponseEntity<Page<TaskSelectAllDataDTO>> allTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.allTasks(pageable));
    }

    @GetMapping("/myTasks")
    public ResponseEntity<Page<List<TaskSelectAllDataDTO>>> myTasks(Pageable page, Authentication auth){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.myTasks(page, auth));

    }

    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long idTask){
        taskService.deleteTask(idTask);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateTask/{categoryId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable long id ,@RequestBody @Valid TaskRequest request){
        return ResponseEntity.ok(taskService.updateTask(id ,request));
    }

    @GetMapping("/fillTasks")
    public ResponseEntity<List<TaskResponse>> searchFilterTask(TaskFilter filter){
        return ResponseEntity.ok(taskService.filterTask(filter));

    }
}
