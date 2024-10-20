package com.invntree.product.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String productId;
    private String name;
    private BigDecimal price;
    private Float rating;
    private Integer stockQuantity;
}
