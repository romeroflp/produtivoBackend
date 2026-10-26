package com.example.produtivoBackend.domain.tasks.entity;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.tasks.enums.StatusTask;
import com.example.produtivoBackend.domain.users.entities.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private Users users;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_task")
    private StatusTask statusTask;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "created_in")
    private LocalDateTime createdIn;

    private Boolean recurrent;

    public Task(Long id,Users users, String title, String description, Category category, StatusTask statusTask, LocalDateTime expirationDate, Boolean recurrent) {
        this.id = id;
        this.users = users;
        this.title = title;
        this.description = description;
        this.category = category;
        this.statusTask = statusTask;
        this.createdIn = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.recurrent = recurrent;
    }

    public Task( Users user,TaskRequest request, Category category){
        this.users = user;
        this.title = request.title();
        this.description = request.description();
        this.category = category;
        this.statusTask = request.statusTask();
        this.createdIn = LocalDateTime.now();
        this.expirationDate = request.expirationDate();
        this.recurrent = request.recurrent();
    }

    public Task(Users users, String title, String description, Category category, StatusTask statusTask, LocalDateTime expirationDate, Boolean recurrent) {
        this.users = users;
        this.title = title;
        this.description = description;
        this.category = category;
        this.statusTask = statusTask;
        this.createdIn = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.recurrent = recurrent;
    }

    public Task from(TaskRequest request, Users user,Category category ){
        return new Task(request.id(),
                user,
                request.title(),
                request.description(),
                category,
                request.statusTask(),
                request.expirationDate(),
                request.recurrent()

        );
    }

}
