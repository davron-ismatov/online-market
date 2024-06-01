package com.example.product.controller;

import com.example.product.dto.ProductCreateDTO;
import com.example.product.dto.ProductDTO;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController  {
    private final ProductService service;
//    @GetMapping("/hello")
//    public String hello() {
//        log.info("GATEWAY");
//        return "hello";
//    }
    @GetMapping
    public List<ProductDTO> getProducts() {
        return service.getProducts();
    }
    @GetMapping("/{id}")
    public ProductDTO getProductByID(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductCreateDTO createDTO) {
        return service.createProduct(createDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductCreateDTO createDTO, @PathVariable Long id) {
        return service.updateProduct(createDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}
