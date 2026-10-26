package com.example.produtivoBackend.domain.tasks.services;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.InvalidCategoryOrNotExistException;
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

    public List<CategoryResponseDTO> listCategories(Authentication auth){
        Users user = userRepo.findByEmail(auth.getName());
        List<Category> categories = catRepo.findByUsersOrUsersIsNull(user);

        return categories.stream().map(CategoryResponseDTO::new).toList();
    }

    public CategoryResponseDTO createCategories(CategoryRequestDTO requestCategory, Authentication auth){
        String email = auth.getName();
        Users user = userRepo.findByEmail(email);
        Category category = catRepo.save(new Category(requestCategory.name(), requestCategory.colorIcon(), user));
        return new CategoryResponseDTO(category);

    }

//    public List<CategoryResponseDTO> listMyCategories(String email){
//        UUID userId = userRepo.findByEmail(email).getId();
//        return catRepo.findAllByUsersId(userId).stream().map(CategoryResponseDTO::new).toList();
//    }

    public CategoryResponseDTO updateCategory(CategoryRequestDTO request, Authentication auth){
        Users user = userRepo.findByEmail(auth.getName());
        long countDefaultCategories = catRepo.findAll().stream().filter(c -> c.getUsers() == null).count();
        if(request.id() <= countDefaultCategories && !catRepo.existsById(request.id())){
            throw new InvalidCategoryOrNotExistException("Não foi possível atualizar essa categoria.");
        }
        Category cat = catRepo.save(new Category(request.id(), request.name(), request.colorIcon(), user));
        return new CategoryResponseDTO(cat);
    }

    public void deleteCategory(Long id, Authentication auth){
        long countDefaultCategories = catRepo.findAll().stream().filter(c -> c.getUsers() == null).count();
        String email = catRepo.findById(id).orElseThrow(InvalidCategoryOrNotExistException::new).getUsers().getEmail();
        if(id <= countDefaultCategories && !catRepo.existsById(id) || !auth.getName().equals(email)){
            throw new InvalidCategoryOrNotExistException("Não foi possível deletar essa categoria.");
        }
        catRepo.deleteById(id);
    }


}
