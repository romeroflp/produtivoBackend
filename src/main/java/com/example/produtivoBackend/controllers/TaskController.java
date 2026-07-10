package com.example.produtivoBackend.controllers;


import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController{
    @Autowired
    private TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskRequest taskRequest){
        TaskResponse response = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<TaskResponse> searchTask (@PathVariable @Positive Long idTask){
        return ResponseEntity.ok(taskService.searchTask(idTask));

    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<TaskResponse>> allTasks(){
        return ResponseEntity.ok(taskService.allTasks());
    }

    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long idTask){
        taskService.deleteTask(idTask);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/alterTask/{idTask}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long idTask,@RequestBody @Valid TaskRequest request){
        return ResponseEntity.ok(taskService.updateTask(idTask, request));
    }

    public ResponseEntity<TaskResponse> filterTask(@RequestParam TaskRequest request){


    }
}
