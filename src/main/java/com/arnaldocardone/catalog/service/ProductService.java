package com.arnaldocardone.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnaldocardone.catalog.domain.category.Category;
import com.arnaldocardone.catalog.domain.category.exceptions.CategoryNotFoundException;
import com.arnaldocardone.catalog.domain.product.Product;
import com.arnaldocardone.catalog.domain.product.ProductDTO;
import com.arnaldocardone.catalog.domain.product.exceptions.ProductNotFoundException;
import com.arnaldocardone.catalog.repositories.ProductRepository;

@Service
public class ProductService {
    
     @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public Product insertProduct(ProductDTO productData){

        Category category = this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product updateProduct(String id, ProductDTO productData){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        //Verifica se foram passados os dados para atualizar
        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());
        
        //Verifica se a categoria existe e atualiza os dados
        this.categoryService.getById(productData.categoryId()).ifPresent(product::setCategory); 
         
        this.repository.save(product);
        return product;
    }

    public void deleteProduct(String id){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);

    }

    public List<Product> listAllProduct(){
        return this.repository.findAll();
    }
}
