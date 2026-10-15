package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import com.example.produtivoBackend.domain.users.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
                                        JpaSpecificationExecutor<Task>,
                                        PagingAndSortingRepository<Task,Long> {
    Optional<Task> findById(Long idTask);
    Optional<Task> findByTitleContaining(String title);

    @Query(value = """
        SELECT new com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO(t.users.id, t.title, t.description, t.createdIn,
        t.expirationDate, c.id, c.name, CAST(t.statusTask AS String), t.recurrent, c.colorIcon) FROM Task AS t
        INNER JOIN t.category AS c
        WHERE t.users.id = :userId
    """)
    Page<List<TaskSelectAllDataDTO>> findAllByUsers(Pageable page, UUID userId);

    @Query(value = """
        SELECT new com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO(t.users.id, t.title, t.description, t.createdIn,
        t.expirationDate, c.id, c.name, CAST(t.statusTask AS String), t.recurrent, c.colorIcon) FROM Task AS t
        INNER JOIN t.category AS c
    """)
    Page<TaskSelectAllDataDTO> findAllWithCategory(Pageable pageable);


}
