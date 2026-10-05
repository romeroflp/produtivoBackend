package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
                                        JpaSpecificationExecutor<Task>,
                                        PagingAndSortingRepository<Task,Long> {
    Optional<Task> findById(Long idTask);

}
