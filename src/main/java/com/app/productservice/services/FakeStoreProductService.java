package com.app.productservice.services;

import com.app.productservice.dtos.FakeStoreProductDto;
import com.app.productservice.models.Category;
import com.app.productservice.models.Product;
import com.app.productservice.services.ProductService;
import exceptions.ProductNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        FakeStoreProductDto dto = restTemplate.getForObject(url, FakeStoreProductDto.class);

        if (dto == null) {
            throw new ProductNotFoundException(id, "Product with id " + id + " not found");
        }

        // Convert FakeStoreProductDto to Product
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] dtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        // Convert FakeStoreProductDto[] to Product[]
        List<Product> products = new ArrayList<>();

        assert dtos != null;
        for (FakeStoreProductDto dto : dtos) {
            products.add(convertFakeStoreDtoToProduct(dto));
        }

        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        assert fakeStoreProductDto != null;
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        assert response != null;
        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}