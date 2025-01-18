package com.arnaldocardone.catalog.controllers;

import com.arnaldocardone.catalog.domain.category.Category;
import com.arnaldocardone.catalog.domain.category.CategoryDTO;
import com.arnaldocardone.catalog.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> insertCategory(@RequestBody CategoryDTO categoryData){
        Category categoryNew = service.insertCategory(categoryData);
        return ResponseEntity.ok().body(categoryNew);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData){

        Category categoryNew = service.updateCategory(id, categoryData);
        return ResponseEntity.ok().body(categoryNew);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAllCategory(){
        List<Category> categories = service.listAllCategory();
        return ResponseEntity.ok().body(categories);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String id){

        this.service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
