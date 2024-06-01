package com.example.order.dto;

import com.example.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    private Long productId;
    private Long userId;
    private Long productAmount;
}
