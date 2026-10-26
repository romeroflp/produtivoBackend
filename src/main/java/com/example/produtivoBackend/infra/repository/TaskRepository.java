package com.example.produtivoBackend.infra.repository;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO;
import com.example.produtivoBackend.domain.tasks.entity.Task;
import com.example.produtivoBackend.domain.users.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
                                        JpaSpecificationExecutor<Task>,
                                        PagingAndSortingRepository<Task,Long> {
    Optional<Task> findById(Long idTask);

    @Query("""
           SELECT t FROM Task t
           WHERE LOWER(t.title) LIKE CONCAT('%', LOWER(:title), '%') AND
           t.users.id = :id
           """)
    Optional<List<Task>> findByTitleContaining(String title, UUID id);

    // O ideal é retornar a entidade e criar o DTO TaskSelectAllDataDto apartir dele dentro do service
    @Query(value = """
        SELECT new com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO(t.id,t.users.id, t.title, t.description, t.createdIn,
        t.expirationDate, c.id, c.name, CAST(t.statusTask AS String), t.recurrent, c.colorIcon) FROM Task AS t
        INNER JOIN t.category AS c
        WHERE t.users.id = :userId
    """)
    Page<List<TaskSelectAllDataDTO>> findAllByUsers(Pageable page, UUID userId);

//    @Query(value = """
//        SELECT new com.example.produtivoBackend.domain.tasks.DTOs.TaskSelectAllDataDTO(t.users.categoryId, t.title, t.description, t.createdIn,
//        t.expirationDate, c.categoryId, c.name, CAST(t.statusTask AS String), t.recurrent, c.colorIcon) FROM Task AS t
//        INNER JOIN t.category AS c
//    """)
//    Page<TaskSelectAllDataDTO> findAllWithCategory(Pageable pageable);


}
