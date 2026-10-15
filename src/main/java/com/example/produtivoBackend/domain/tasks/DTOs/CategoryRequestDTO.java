package com.example.produtivoBackend.domain.tasks.DTOs;

import com.example.produtivoBackend.domain.tasks.services.CategoryService;

public record CategoryRequestDTO(Long id , String name, String colorIcon) {
    public CategoryRequestDTO(String name, String colorIcon){
        this(null, name, colorIcon);
    }
}
