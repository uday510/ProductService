package com.app.productservice.controllers;

import com.app.productservice.models.Product;
import com.app.productservice.services.ProductService;
import exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> response;

        if (product == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        }

        response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productService.replaceProduct(id, newProduct);
    }

    @PostMapping
   public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}