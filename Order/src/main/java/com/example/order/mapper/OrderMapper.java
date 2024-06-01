package com.example.order.mapper;

import com.example.order.dto.OrderDTO;
import com.example.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order>{
}
