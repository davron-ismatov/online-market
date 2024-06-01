package com.example.order.mapper;

import com.example.order.dto.OrderCreateDTO;
import com.example.order.entity.Order;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderCreateMapper extends EntityMapper<OrderCreateDTO,Order>{
}
