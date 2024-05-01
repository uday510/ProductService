package com.app.productservice.services;

import com.app.productservice.models.Category;
import com.app.productservice.models.Product;
import com.app.productservice.repositories.CategoryRepository;
import com.app.productservice.repositories.ProductRepository;
import exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id, "Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
           return null;
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct() {

    }
}
