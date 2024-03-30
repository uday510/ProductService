package com.app.productservice.services;

import com.app.productservice.dtos.FakeStoreProductDto;
import com.app.productservice.models.Category;
import com.app.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductService implements ProductService{
    private final RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        String url = "https://fakestoreapi.com/products/" + id;

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(url, FakeStoreProductDto.class);

        assert fakeStoreProductDto != null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    /**
     *
     * @return List of products
     *
     * This method fetches the list of products from the fakestoreapi.com
     * and converts the response to a list of products
     *
     * @return List of products
     *
     * @see Product
     */
    @Override
    public List<Product> getProducts() {
        String url = "https://fakestoreapi.com/products";

        FakeStoreProductDto[] fakeStoreProductsDto = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        assert fakeStoreProductsDto != null;

        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductsDto) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }
}


