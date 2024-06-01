package com.example.order.dto;

import com.example.feignclients.auth.UserInfo;
import com.example.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime orderedDate;
    private Long productId;
    private Long userId;
    private Long productAmount;
    private OrderStatus orderStatus;
    private UserInfo userInfo;
}
