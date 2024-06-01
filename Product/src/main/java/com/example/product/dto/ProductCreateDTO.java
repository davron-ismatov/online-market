package com.example.product.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {
    private String name;
    private Long count;
    private Double price;
    private String description;
}
