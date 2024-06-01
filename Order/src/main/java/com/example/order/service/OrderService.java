package com.example.order.service;

import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderWithUsersDTO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrder(Long id);
    List<OrderDTO> getOrderByUserId(Long id);
    OrderWithUsersDTO getOrderWithFullInfo(Long id);
    List<OrderWithUsersDTO> getOrdersWithFullInfo();
    OrderDTO createOrder(OrderCreateDTO createDTO) throws UserPrincipalNotFoundException;
    List<OrderDTO> createOrder(List<OrderCreateDTO> createDTO) throws UserPrincipalNotFoundException;

    void deleteOrder(Long id);
}
