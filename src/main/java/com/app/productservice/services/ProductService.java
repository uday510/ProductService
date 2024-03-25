package com.app.productservice.services;
import com.app.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getProducts();
}
