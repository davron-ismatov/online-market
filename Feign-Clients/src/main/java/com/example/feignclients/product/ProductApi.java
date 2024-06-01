package com.example.feignclients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("PRODUCT-SERVICE")
@Component
public interface ProductApi {
    @GetMapping("/product-feign")
    List<ProductInfo> getAllProductsInfo();

    @GetMapping("/product-feign/{id}")
    ProductInfo getProductInfo(@PathVariable(name = "id") String id);

    @GetMapping("check-existence/{id}")
    Boolean isExists(@PathVariable(name = "id") String id);
}
