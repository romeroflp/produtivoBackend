package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.tasks.DTOs.CategoryRequestDTO;
import com.example.produtivoBackend.domain.tasks.DTOs.CategoryResponseDTO;
import com.example.produtivoBackend.domain.tasks.entity.Category;
import com.example.produtivoBackend.domain.tasks.services.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService catService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(catService.findAll());
    }

    @GetMapping("/myCategories")
    public ResponseEntity<List<CategoryResponseDTO>> listMyCategories(Authentication auth){
        String email = auth.getName();
        return ResponseEntity.status(HttpStatus.OK).body(catService.listMyCategories(email));

    }

    @PostMapping("/save")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO request, Authentication user){
        return ResponseEntity.status(HttpStatus.CREATED).body(catService.createCategories(request, user));
    }

    @PatchMapping("/updateCategory")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryRequestDTO request, Authentication auth){
        return ResponseEntity.status(HttpStatus.OK).body(catService.updateCategory(request, auth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        catService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }




}
