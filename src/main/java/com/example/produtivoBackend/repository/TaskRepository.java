package com.example.produtivoBackend.repository;

import com.example.produtivoBackend.domain.tasks.Task;
import com.example.produtivoBackend.domain.tasks.TaskDTO;
import com.example.produtivoBackend.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
