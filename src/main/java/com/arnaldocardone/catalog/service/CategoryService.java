package com.arnaldocardone.catalog.service;

import com.arnaldocardone.catalog.domain.category.Category;
import com.arnaldocardone.catalog.domain.category.CategoryDTO;
import com.arnaldocardone.catalog.domain.category.exceptions.CategoryNotFoundException;
import com.arnaldocardone.catalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category insertCategory(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category updateCategory(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        //Verifica se foram passados os dados para atualizar
        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.repository.save(category);
        return category;
    }

    public void deleteCategory(String id){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);

    }

    public List<Category> listAllCategory(){
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }

}
