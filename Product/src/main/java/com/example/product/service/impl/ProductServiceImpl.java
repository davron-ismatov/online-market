package com.example.product.service.impl;

import com.example.feignclients.product.ProductInfo;
import com.example.product.dto.ProductCreateDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductCreateMapper;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCreateMapper createMapper;
    private final ProductMapper mapper;

    @Override
    public List<ProductDTO> getProducts() {
        return mapper.toDTOs(productRepository.findAll());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.toDto(productRepository.getReferenceById(id));
    }
    @Override
    public ProductDTO createProduct(ProductCreateDTO createDTO) {
        return mapper.toDto(
                productRepository.save(createMapper.toEntity(createDTO))
        );
    }

    @Override
    public ProductDTO updateProduct(ProductCreateDTO createDTO, Long id) {
        Product product = productRepository.getReferenceById(id);
        product.setCount(createDTO.getCount());
        product.setName(createDTO.getName());
        product.setPrice(createDTO.getPrice());
        product.setDescription(createDTO.getDescription());
        return mapper.toDto(
                productRepository.save(product)
        );
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductInfo> getAllProductsInfo(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductInfo getProductInfo(Long id){
        Product product = productRepository.getReferenceById(id);
        return toDTO(product);
    }

    @Override
    public Boolean isExists(Long id) {
        return productRepository.existsById(id);
    }

    private ProductInfo toDTO(Product product){
        return ProductInfo.builder()
                .count(product.getCount())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .id(product.getId())
                .build();
    }
}
