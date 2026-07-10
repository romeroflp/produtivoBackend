package com.example.produtivoBackend.services;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.DTOs.TaskResponse;
import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.users.Users;
import com.example.produtivoBackend.repository.SpecificationTask;
import com.example.produtivoBackend.repository.TaskRepository;
import com.example.produtivoBackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

import static com.example.produtivoBackend.repository.SpecificationTask.hasCategory;

@Service
public class TaskService{
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;

    public TaskResponse createTask(TaskRequest taskRequest){

        Users user = userRepo.findById(taskRequest.userId())
                .orElseThrow(RuntimeException::new);
        Task task = taskRepo.save(new Task(
                                  user,
                                  taskRequest.title(),
                                  taskRequest.description(),
                                  taskRequest.category(),
                                  taskRequest.status_task(),
                                  taskRequest.expiration_date(),
                                  taskRequest.recurrent()));

        return new TaskResponse(task);
    }

    public TaskResponse searchTask(Long idTask){
        return new TaskResponse(taskRepo.getReferenceById(idTask));
    }

    public List<TaskResponse> allTasks(){
        return taskRepo.findAll().stream().map(TaskResponse::new).toList();
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    public TaskResponse updateTask(Long idTask,TaskRequest request){
        Task task = taskRepo.findById(idTask)
                .orElseThrow(() -> new RuntimeException("Essa task não existe."));

        BeanUtils.copyProperties(request, task);
        taskRepo.save(task);
        return new TaskResponse(task);
    }

//    public <T> List<TaskResponse> filterTasks(T filter){
//        return allTasks().stream().filter(t -> t)
//    }

    public void filter(TaskRequest request){
        Users user = userRepo.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Nenhum usuário foi encontrado."));
        Task task = new Task(user ,request);
        Specification<Task> responseSpecification = Specification.unrestricted();

        if(request.category() != null){
            responseSpecification = responseSpecification.and(hasCategory(request.category()));
        }



    }


}
