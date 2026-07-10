package com.example.produtivoBackend.domain.tasks;

import com.example.produtivoBackend.domain.tasks.DTOs.TaskRequest;
import com.example.produtivoBackend.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryTask category;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusTask")
    private StatusTask statusTask;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "created_in")
    private LocalDateTime createdIn;

    private boolean recurrent;

    public Task(Users users, String title, String description, CategoryTask category, StatusTask statusTask, LocalDateTime expirationDate,boolean recurrent) {
        this.users = users;
        this.title = title;
        this.description = description;
        this.category = category;
        this.statusTask = statusTask;
        this.createdIn = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.recurrent = recurrent;
    }

    public Task( Users user,TaskRequest request){
        this.users = user;
        this.title = request.title();
        this.description = request.description();
        this.category = request.category();
        this.statusTask = request.statusTask();
        this.expirationDate = request.expirationDate();
        this.recurrent = request.recurrent();
    }

}
