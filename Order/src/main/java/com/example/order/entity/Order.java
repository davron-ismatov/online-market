package com.example.order.entity;

import com.example.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "order_tbl")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderedDate;

    private Long productId;
    private Long productAmount;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @PrePersist
    private void prePersist(){
        if (orderedDate==null && orderStatus==null){
            this.orderedDate = LocalDateTime.now();
            this.orderStatus = OrderStatus.PACKAGING;
        }
    }
}
