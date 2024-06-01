package com.example.product.service;

import com.example.feignclients.product.ProductInfo;
import com.example.product.dto.ProductCreateDTO;
import com.example.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();
    ProductDTO getProductById(Long id);


    ProductDTO createProduct(ProductCreateDTO createDTO);

    ProductDTO updateProduct(ProductCreateDTO createDTO, Long id);

    void deleteProduct(Long id);

    List<ProductInfo> getAllProductsInfo();

    ProductInfo getProductInfo(Long id);

    Boolean isExists(Long id);

}
