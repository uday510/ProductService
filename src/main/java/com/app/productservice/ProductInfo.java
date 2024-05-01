package com.app.productservice;

/**
 * Projection for {@link com.app.productservice.models.Product}
 */
public interface ProductInfo {
    String getTitle();

    double getPrice();

    String getImage();
}