package com.example.feignclients.product;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfo {
    private Long id;
    private String name;
    private Long count;
    private Double price;
    private String description;
}
