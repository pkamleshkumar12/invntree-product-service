package com.invntree.product.service;

import com.invntree.product.model.Product;
import com.invntree.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findByIsDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(String productId) {
        return productRepository.findByProductIdAndIsDeletedFalse(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByMinimumStock(Integer minStockQuantity) {
        return productRepository.findByStockQuantityGreaterThanEqualAndIsDeletedFalse(minStockQuantity);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByMinimumRating(Float minRating) {
        return productRepository.findByRatingGreaterThanEqualAndIsDeletedFalse(minRating);
    }

    @Transactional
    public Product createProduct(Product product) {
        product.setCreatedDateTime(LocalDateTime.now());
        product.setUpdatedDateTime(LocalDateTime.now());
        product.setIsDeleted(false);
        return productRepository.save(product);
    }

    @Transactional
    public Optional<Product> updateProduct(String productId, Product updatedProduct) {
        return productRepository.findByProductIdAndIsDeletedFalse(productId)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setRating(updatedProduct.getRating());
                    existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
                    existingProduct.setUpdatedDateTime(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                });
    }

    @Transactional
    public boolean deleteProduct(String productId) {
        return productRepository.findByProductIdAndIsDeletedFalse(productId)
                .map(product -> {
                    product.setIsDeleted(true);
                    product.setUpdatedDateTime(LocalDateTime.now());
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }
}
