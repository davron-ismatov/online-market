package com.example.product.controller;

import com.example.feignclients.product.ProductInfo;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-feign")
@RequiredArgsConstructor
public class ProductFeignController {
    private final ProductService productService;

    @GetMapping
    public List<ProductInfo> getAllProductsInfo(){
        return productService.getAllProductsInfo();
    }

    @GetMapping("/{id}")
    public ProductInfo getProductInfo(@PathVariable Long id){
        return productService.getProductInfo(id);
    }

    @GetMapping("check-existence/{id}")
    public Boolean isExists(@PathVariable Long id){
        return productService.isExists(id);
    }
}
