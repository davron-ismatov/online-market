package com.example.order.service.impl;

import com.example.feignclients.auth.UserInfo;
import com.example.feignclients.product.ProductApi;
import com.example.feignclients.product.ProductInfo;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderWithUsersDTO;
import com.example.order.entity.Order;
import com.example.order.enums.OrderStatus;
import com.example.order.mapper.OrderCreateMapper;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.example.feignclients.auth.AuthApi;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AuthApi authApi;
    private final ProductApi productApi;
    private final OrderCreateMapper createMapper;
    private final OrderMapper mapper;
    @Override
    public List<OrderDTO> getAllOrders() {
        return mapper.toDTOs(orderRepository.findAll());
    }

    @Override
    public OrderDTO getOrder(Long id) {
        return mapper.toDTO(orderRepository.getReferenceById(id));
    }

    @Override
    public List<OrderDTO> getOrderByUserId(Long id) {
        return mapper.toDTOs(orderRepository.getOrderByUserId(id));
    }

    @Override
    public OrderWithUsersDTO getOrderWithFullInfo(Long id) {
        Order order = orderRepository.getReferenceById(id);
        ProductInfo productInfo = productApi.getProductInfo(String.valueOf(order.getProductId()));
        UserInfo userInfo = authApi.getUserForOrder(String.valueOf(order.getUserId()));

        return toOrderWithUsersDTO(order,productInfo,userInfo);
    }

    @Override
    public List<OrderWithUsersDTO> getOrdersWithFullInfo() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    ProductInfo productInfo = productApi.getProductInfo(String.valueOf(order.getProductId()));
                    UserInfo userInfo = authApi.getUserForOrder(String.valueOf(order.getUserId()));
                    return toOrderWithUsersDTO(order,productInfo,userInfo);
                }).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderCreateDTO createDTO) throws UserPrincipalNotFoundException {

        try {
            UserInfo userForOrder = authApi.getUserForOrder(String.valueOf(createDTO.getUserId()));
            ProductInfo productInfo = productApi.getProductInfo(String.valueOf(createDTO.getProductId()));
            if (!userForOrder.getIsEnabled()){
                throw new IllegalArgumentException("You can not create an order");
            }

            if (productInfo.getCount()>=createDTO.getProductAmount()) {
                Order save = orderRepository.save(createMapper.toEntity(createDTO));
                return mapper.toDTO(save);
            }else {
                throw new IllegalArgumentException("Insufficient resources!");
            }
        }catch (Exception e){
            throw new UserPrincipalNotFoundException("User or product not found");
        }

    }

    @Override
    public List<OrderDTO> createOrder(List<OrderCreateDTO> createDTO) throws UserPrincipalNotFoundException {
        try {
            List<OrderDTO> orderDTOS = new ArrayList<>();
            for (OrderCreateDTO orderCreateDTO : createDTO) {
                UserInfo userForOrder = authApi.getUserForOrder(String.valueOf(orderCreateDTO.getUserId()));
                ProductInfo productInfo = productApi.getProductInfo(String.valueOf(orderCreateDTO.getProductId()));
                if (!userForOrder.getIsEnabled()){
                    throw new IllegalArgumentException("You can not create an order");
                }
                if (productInfo.getCount()>=orderCreateDTO.getProductAmount()){
                    Order save = orderRepository.save(createMapper.toEntity(orderCreateDTO));
                    orderDTOS.add(mapper.toDTO(save));
                }else {
                    throw new IllegalArgumentException("Insufficient resources!");
                }

            }
            return orderDTOS;
        }catch (Exception e){
            throw new UserPrincipalNotFoundException(e.getMessage());
        }

    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.getReferenceById(id);
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }


    private OrderWithUsersDTO toOrderWithUsersDTO(Order order,ProductInfo productInfo,UserInfo userInfo){
        return OrderWithUsersDTO.builder()
                .orderedDate(order.getOrderedDate())
                .orderStatus(order.getOrderStatus())
                .userInfo(userInfo)
                .productId(productInfo)
                .id(order.getId())
                .build();
    }
}
