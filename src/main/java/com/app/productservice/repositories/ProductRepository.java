package com.app.productservice.repositories;

import com.app.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    // select * from product where title = title
    List<Product> findByTitle(String title);

    // select * from product where title like %title%
    List<Product> findByTitleContains(String title);

    // select * from product where title like %title% and description like %description%
    List<Product> findByTitleAndDescription(String title, String description);

    // select * from product where title like %title% or description like %description%
    Optional<Product> findByImage(String image);


    @Override
    void delete(Product product);

    // for create and update, we have to use save method, which is inherited from JpaRepository
    // save method will insert a new record if the record does not exist, else it will update the existing record
    Product save(Product product);


    // HQL Query
    @Query("SELECT p.title as title, p.description as description FROM Product p WHERE p.title = ?1")
    List<Product> getProductsByTitle(@Param("title") String title);
}
