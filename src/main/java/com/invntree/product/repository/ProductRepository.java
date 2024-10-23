package com.invntree.product.repository;

import com.invntree.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    // Find all non-deleted products
    List<Product> findByIsDeletedFalse();

    // Find a non-deleted product by ID
    Optional<Product> findByProductIdAndIsDeletedFalse(String productId);

    // Find products by name (case-insensitive, partial match)
    List<Product> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);

    // Find products with stock quantity greater than or equal to a given value
    List<Product> findByStockQuantityGreaterThanEqualAndIsDeletedFalse(Integer minStockQuantity);

    // Find products with a rating greater than or equal to a given value
    List<Product> findByRatingGreaterThanEqualAndIsDeletedFalse(Float minRating);
}
