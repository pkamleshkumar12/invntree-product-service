package com.invntree.product.controller;

import com.invntree.product.dto.ApiResponse;
import com.invntree.product.dto.ProductDTO;
import com.invntree.product.mapper.ProductMapper;
import com.invntree.product.model.Product;
import com.invntree.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Products retrieved successfully", productDTOs));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId)
                .map(product -> {
                    ProductDTO productDTO = productMapper.toDTO(product);
                    return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Product retrieved successfully", productDTO));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Product not found", null)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Products searched successfully", productDTOs));
    }

    @GetMapping("/minimum-stock")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByMinimumStock(@RequestParam Integer minStockQuantity) {
        List<Product> products = productService.getProductsByMinimumStock(minStockQuantity);
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Products retrieved successfully", productDTOs));
    }

    @GetMapping("/minimum-rating")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByMinimumRating(@RequestParam Float minRating) {
        List<Product> products = productService.getProductsByMinimumRating(minRating);
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Products retrieved successfully", productDTOs));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        ProductDTO createdProductDTO = productMapper.toDTO(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Product created successfully", createdProductDTO));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable String productId, @RequestBody ProductDTO updatedProductDTO) {
        Product updatedProduct = productMapper.toEntity(updatedProductDTO);
        return productService.updateProduct(productId, updatedProduct)
                .map(product -> {
                    ProductDTO productDTO = productMapper.toDTO(product);
                    return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Product updated successfully", productDTO));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Product not found", null)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String productId) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Product deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Product not found", null));
        }
    }
}
