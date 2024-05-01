package com.app.productservice;
import com.app.productservice.controllers.ProductController;
import com.app.productservice.models.Product;
import com.app.productservice.services.ProductService;
import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
Arrange
Act
Assert
 */

@SpringBootTest
public class RandomTest {

    @Autowired
    private ProductController productController;

    @MockBean // Mocking the ProductService
    private ProductService productService;

    @Test
    void validGetProductByIdTest() throws ProductNotFoundException {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setPrice(100.0);

        when(productService.getProductById(1L))
                .thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1L);
        Product actualProduct = response.getBody();


        assertEquals(product, actualProduct);
    }

    @Test
    void testAllProducts() {

        List<Product> expectedProducts = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setPrice(100.0);
        expectedProducts.add(product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setPrice(200.0);
        expectedProducts.add(product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("Product 3");
        product3.setPrice(300.0);
        expectedProducts.add(product3);

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        List<Product> products = productController.getAllProducts();

        for (Product product : products) {
           for (Product expectedProduct : expectedProducts) {
               if (Objects.equals(product.getId(), expectedProduct.getId())) {
                   assertEquals(product, expectedProduct);
               }
           }
        }

        assertIterableEquals(expectedProducts, products);
    }
}
