package com.example.produtivoBackend.controllers;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.taskService.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<TaskResponse>> allTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.allTasks(pageable));
    }

    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive Long idTask){
        taskService.deleteTask(idTask);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/alterTask/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable long id ,@RequestBody @Valid TaskRequest request){
        return ResponseEntity.ok(taskService.updateTask(id ,request));
    }

    @GetMapping("/fillTasks")
    public ResponseEntity<List<TaskResponse>> searchFilterTask(TaskFilter filter){
        return ResponseEntity.ok(taskService.filterTask(filter));

    }
}
