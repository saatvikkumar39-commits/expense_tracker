package com.saatvik.expense_tracker.service;

import com.saatvik.expense_tracker.dto.CategoryRequest;
import com.saatvik.expense_tracker.dto.CategoryResponse;
import com.saatvik.expense_tracker.model.Category;
import com.saatvik.expense_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private CategoryResponse toResponse(Category category){
        CategoryResponse response=new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    public CategoryResponse create(CategoryRequest request){
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists: " + request.getName());
        }
        Category category=new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category saved=categoryRepository.save(category);
        return toResponse(saved);
    }


    public List<CategoryResponse> getAll(){
        return categoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CategoryResponse getById(Long id){
        Category selected=categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return toResponse(selected);
    }

    public CategoryResponse update(Long id,CategoryRequest request){
        Category category=categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found with id:"+id));
        category.setName(request.getName());
        category.setDescription((request.getDescription()));
        return toResponse(category);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

}
