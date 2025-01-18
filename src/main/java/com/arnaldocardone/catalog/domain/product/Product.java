package com.arnaldocardone.catalog.domain.product;

import com.arnaldocardone.catalog.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price; //Sempre grava inteiro e faço a divisão ao recuperar e a multiplicação ao salvar
    private Category category;

    public Product(ProductDTO productData) {
        this.title = productData.title();
        this.description = productData.description();
        this.ownerId = productData.ownerId();
        this.price = productData.price();
    }
}
