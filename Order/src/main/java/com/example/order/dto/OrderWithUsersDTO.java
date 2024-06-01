package com.example.order.dto;

import com.example.feignclients.auth.UserInfo;
import com.example.feignclients.product.ProductInfo;
import com.example.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithUsersDTO {
    private Long id;
    private LocalDateTime orderedDate;
    private ProductInfo productId;
    private OrderStatus orderStatus;
    private Long productAmount;
    private UserInfo userInfo;
}
