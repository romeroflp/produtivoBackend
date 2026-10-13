package com.example.produtivoBackend.domain.tasks.entity;

import com.example.produtivoBackend.domain.users.entities.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;
    @Pattern(regexp = "^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6})$", message = "A cor do ícone deve estar no formato hexadecimal.")
    private String colorIcon;

    public Category(String name, Users user, String colorIcon){
        this.name = name;
        this.users = user;
        this.colorIcon = colorIcon;
    }
}


