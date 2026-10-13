package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.entity.Category;

import java.util.UUID;

public record CategoryResponseDTO(Long id, String name,UUID userId, String colorIcon) {
    public CategoryResponseDTO(Category category){
        this(   category.getId(),
                category.getName(),
                category.getUsers() != null ? category.getUsers().getId() : null ,
                category.getColorIcon()
        );
    }

}
