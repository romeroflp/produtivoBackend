package com.example.produtivoBackend.domain.tasks;

import com.example.produtivoBackend.domain.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tasks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryTask category;
    @Enumerated(EnumType.STRING)
    private StatusTask status_task;
    private LocalDateTime expiration_date;
    private LocalDateTime created_in = LocalDateTime.now();
    private boolean recurrent;


    public Task(Users users, String title, String description, CategoryTask category, StatusTask status_task, LocalDateTime expiration_date,boolean recurrent) {
        this.users = users;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status_task = status_task;
        this.created_in = LocalDateTime.now();
        this.expiration_date = expiration_date;
        this.recurrent = recurrent;
    }
}
