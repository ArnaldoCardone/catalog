package com.arnaldocardone.catalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnaldocardone.catalog.domain.product.Product;
import com.arnaldocardone.catalog.domain.product.ProductDTO;
import com.arnaldocardone.catalog.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody ProductDTO productData){
        Product productNew = service.insertProduct(productData);
        return ResponseEntity.ok().body(productNew);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productData){

        Product productNew = service.updateProduct(id, productData);
        return ResponseEntity.ok().body(productNew);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProduct(){
        List<Product> products = service.listAllProduct();
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id){

        this.service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
