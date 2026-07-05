package com.example.produtivoBackend.domain.users;

import com.example.produtivoBackend.domain.tasks.Task;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(mappedBy = "users")
    private List<Task> tasks_id;
    private String username;
    private LocalDateTime createdIn = LocalDateTime.now();
}
