package com.example.produtivoBackend.domain.tasks.services;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.InvalidCategoryOrNotExistException;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.TaskNotExistOrInvalidException;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import com.example.produtivoBackend.domain.users.entities.Users;
import com.example.produtivoBackend.infra.repository.CategoryRepository;
import com.example.produtivoBackend.infra.repository.SpecificationTaskRepository;
import com.example.produtivoBackend.infra.repository.TaskRepository;
import com.example.produtivoBackend.infra.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TaskService{

    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryRepository catRepo;

    public TaskResponse createTask(TaskRequest taskRequest, Authentication token){
        String email = token.getName();
        Users user = userRepo.findByEmail(email);
        Category cat = catRepo.findById(taskRequest.categoryId()).orElseThrow(InvalidCategoryOrNotExistException::new);
        if(cat.getUsers().getId() != null && cat.getUsers().getId() != user.getId()){
            throw new InvalidCategoryOrNotExistException("O usuário não possui a categoria definida.");
        }
        Task task = taskRepo.save(new Task(user, taskRequest, cat));

        return new TaskResponse(task);
    }

    public TaskResponse searchTaskById(Long id){
        return new TaskResponse(taskRepo.findById(id).orElseThrow(TaskNotExistOrInvalidException::new));

    }

    public List<TaskResponse> searchTaskByTitle(String identifier, Authentication auth){

        UUID userId = userRepo.findByEmail(auth.getName()).getId();
        List<Task> tasks = taskRepo.findByTitleContaining(identifier, userId).orElseThrow(TaskNotExistOrInvalidException::new);

        return tasks.stream().map(TaskResponse::new).toList();
    }

// Separar e criar um redirecionador dependendo se e um numero ou contem letras. Mudar o regex para verificar se tem alguma letra

    public Page<List<TaskSelectAllDataDTO>> myTasks(Pageable page, Authentication auth){
        UUID userId = userRepo.findByEmail(auth.getName()).getId();
        return taskRepo.findAllByUsers(page, userId);

    }

    public void deleteTask(Long id, Authentication auth){
        Task task = taskRepo.findById(id).orElseThrow(TaskNotExistOrInvalidException::new);
        if(!task.getUsers().getEmail().equals(auth.getName())){
            throw new TaskNotExistOrInvalidException();
        }
        taskRepo.deleteById(id);
    }

    public TaskResponse updateTask(TaskRequest request, Authentication auth){

        Task task = taskRepo.findById(request.id())
                .orElseThrow(TaskNotExistOrInvalidException::new);
        Users user = userRepo.findByEmail(auth.getName());
        Category cat = catRepo.findById(request.categoryId()).orElseThrow(InvalidCategoryOrNotExistException::new);

        if(!user.getId().equals(task.getUsers().getId())){
            throw new TaskNotExistOrInvalidException();
        }

        return new TaskResponse(taskRepo.save(new Task(task.getId(),
                user,
                request.title(),
                request.description(),
                cat,
                request.statusTask(),
                request.expirationDate(),
                request.recurrent()
        )));

    }

    public List<TaskResponse> filterTask(TaskFilter filter, Authentication auth){
        Specification<Task> responseSpecification = Specification.unrestricted();

        if(filter.title() != null){
            responseSpecification = responseSpecification.and(SpecificationTaskRepository.hasTitle(filter.title()));
        }

        if(filter.statusTask() != null){
            responseSpecification = responseSpecification.and(SpecificationTaskRepository.hasStatus(filter.statusTask()));
        }

        if(filter.recurrent() != null){
            responseSpecification = responseSpecification.and(SpecificationTaskRepository.hasRecurrent(filter.recurrent()));
        }

        if(filter.startDate() != null && filter.endDate() == null){
            responseSpecification = responseSpecification.and(SpecificationTaskRepository.hasTaskOnDate(filter.startDate()));
        }

        if(filter.startDate() != null && filter.endDate() != null){
            responseSpecification = responseSpecification.and(SpecificationTaskRepository.hasTaskOnPeriod(filter.startDate(), filter.endDate()));
        }

        //Refatorar tudo isso pra um Builder

        UUID userId = userRepo.findByEmail(auth.getName()).getId();

        List<Task> listTask = taskRepo.findAll(responseSpecification);
        return listTask.stream().filter(t -> t.getUsers().getId() == userId)
                .map(TaskResponse::new).toList();


    }



}
