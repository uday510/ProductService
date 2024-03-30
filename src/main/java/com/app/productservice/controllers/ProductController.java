package com.app.productservice.controllers;

import com.app.productservice.models.Product;
import com.app.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
    @GetMapping("")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
