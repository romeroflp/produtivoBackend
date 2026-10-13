package com.example.produtivoBackend.domain.tasks.services;

import com.example.produtivoBackend.domain.tasks.DTOs.CategoryRequestDTO;
import com.example.produtivoBackend.domain.tasks.DTOs.CategoryResponseDTO;
import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.users.entities.Users;
import com.example.produtivoBackend.infra.repository.CategoryRepository;
import com.example.produtivoBackend.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository catRepo;
    @Autowired
    private UserRepository userRepo;

    public List<CategoryResponseDTO> findAll(){
        List<Category> categories = catRepo.findAll();

        return categories.stream().map(CategoryResponseDTO::new).toList();
    }

    public CategoryResponseDTO createCategories(CategoryRequestDTO requestCategory, Authentication auth){
        String email = auth.getName();
        Users user = userRepo.findByEmail(email);
        Category category = catRepo.save(new Category(requestCategory.name(), user, requestCategory.colorIcon()));
        return new CategoryResponseDTO(category);

    }

    public List<CategoryResponseDTO> listMyCategories(String email){
        UUID userId = userRepo.findByEmail(email).getId();
        return catRepo.findAllByUsersId(userId).stream().map(CategoryResponseDTO::new).toList();
    }


}
