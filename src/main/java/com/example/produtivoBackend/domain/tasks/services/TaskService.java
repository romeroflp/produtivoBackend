package com.example.produtivoBackend.domain.tasks.services;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.CategoryNotExistException;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.TaskNotExistOrInvalidException;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import com.example.produtivoBackend.domain.users.entities.Users;
import com.example.produtivoBackend.infra.repository.CategoryRepository;
import com.example.produtivoBackend.infra.repository.SpecificationTask;
import com.example.produtivoBackend.infra.repository.TaskRepository;
import com.example.produtivoBackend.infra.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Category cat = catRepo.findById(taskRequest.categoryId()).orElseThrow(CategoryNotExistException::new);
        Task task = taskRepo.save(new Task(user, taskRequest, cat));

        return new TaskResponse(task);
    }

    public TaskResponse searchTask(Long idTask){
        return new TaskResponse(taskRepo.findById(idTask).orElseThrow(TaskNotExistOrInvalidException::new));
    }

    //Procurar por nome...

    public Page<TaskSelectAllDataDTO> allTasks(Pageable pageable){
        return taskRepo.findAllWithCategory(pageable);
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    public TaskResponse updateTask(long id,TaskRequest request){

        Task task = taskRepo.findById(id)
                .orElseThrow(TaskNotExistOrInvalidException::new);

        BeanUtils.copyProperties(request, task);
        return new TaskResponse(taskRepo.save(task));

    }


    public List<TaskResponse> filterTask(TaskFilter filter){
        Specification<Task> responseSpecification = Specification.unrestricted();

        if(filter.title() != null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasTitle(filter.title()));
        }

        if(filter.category() != null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasCategory(filter.category()));
        }

        if(filter.statusTask() != null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasStatus(filter.statusTask()));
        }

        if(filter.recurrent() != null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasRecurrent(filter.recurrent()));
        }

        if(filter.expirationDate() != null && filter.expirationTime() == null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasTaskInDate(filter.expirationDate()));
        }

        if(filter.expirationTime() != null && filter.expirationDate() != null){
            responseSpecification = responseSpecification.and(SpecificationTask.hasTaskInDateTime(
                    filter.expirationDate(), filter.expirationTime()));
        }

        //Refatorar tudo isso pra um Builder


        List<Task> listTask = taskRepo.findAll(responseSpecification);
        return listTask.stream().map(TaskResponse::new).toList();

    }
    


}
