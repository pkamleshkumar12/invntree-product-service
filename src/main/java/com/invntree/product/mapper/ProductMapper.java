package com.invntree.product.mapper;

import com.invntree.product.dto.ProductDTO;
import com.invntree.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    List<ProductDTO> toDTOList(List<Product> products);
    Product toEntity(ProductDTO productDTO);
}
