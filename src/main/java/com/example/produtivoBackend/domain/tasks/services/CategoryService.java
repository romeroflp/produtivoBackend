package com.example.produtivoBackend.domain.tasks.services;

import com.example.produtivoBackend.domain.exceptions.GlobalErrorResponse;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.InvalidCredentialsException;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.SimpleErrorMessageException;
import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
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
        Category category = catRepo.save(new Category(requestCategory.name(), requestCategory.colorIcon(), user));
        return new CategoryResponseDTO(category);

    }

    public List<CategoryResponseDTO> listMyCategories(String email){
        UUID userId = userRepo.findByEmail(email).getId();
        return catRepo.findAllByUsersId(userId).stream().map(CategoryResponseDTO::new).toList();
    }

    public CategoryResponseDTO updateCategory(CategoryRequestDTO request, Authentication auth){
        Users user = userRepo.findByEmail(auth.getName());
        Category cat = catRepo.save(new Category(request.id(), request.name(), request.colorIcon(), user));
        return new CategoryResponseDTO(cat);
    }

    public void deleteCategory(Long id){
        long countDefaultCategories = catRepo.findAll().stream().filter(c -> c.getUsers() == null).count();
        if(id <= countDefaultCategories){
            throw new SimpleErrorMessageException("Não é possível deletar as categorias padrões do sistema.") {
            };
        }
        catRepo.deleteById(id);
    }


}
