package com.example.produtivoBackend.domain.tasks.taskService;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskFilter;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;
import com.example.produtivoBackend.repository.SpecificationTask;
import com.example.produtivoBackend.repository.TaskRepository;
import com.example.produtivoBackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService{

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskService(TaskRepository taskRepo, UserRepository userRepo){
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;

    }

    public TaskResponse createTask(TaskRequest taskRequest){

        Users user = userRepo.findById(taskRequest.userId())
                .orElseThrow(RuntimeException::new);
        Task task = taskRepo.save(new Task(user, taskRequest));

        return new TaskResponse(task);
    }

    public TaskResponse searchTask(Long idTask){
        return new TaskResponse(taskRepo.getReferenceById(idTask));
    }

    public Page<TaskResponse> allTasks(Pageable pageable){
        return taskRepo.findAll(pageable).map(TaskResponse::new);
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    public TaskResponse updateTask(long id,TaskRequest request){

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Essa task não existe."));

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
