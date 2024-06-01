package com.example.product.mapper;

import com.example.product.dto.ProductCreateDTO;
import com.example.product.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductCreateMapper extends EntityMapper<ProductCreateDTO, Product>{
}
