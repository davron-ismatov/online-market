package com.example.order.controller;

import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderWithUsersDTO;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController{
    private final OrderService service;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public OrderDTO getOrder(@PathVariable Long orderID) {
        return service.getOrder(orderID);
    }

    @GetMapping("/userId/{userId}")
    public List<OrderDTO> getOrderByUserId(@PathVariable Long userId) {
        return service.getOrderByUserId(userId);
    }

    @GetMapping("/with-full-info/{id}")
    public OrderWithUsersDTO getOrderWithFullInfo(@PathVariable Long id) {
        return service.getOrderWithFullInfo(id);
    }

    @GetMapping("/with-full-info")
    public List<OrderWithUsersDTO> getOrdersWithFullInfo() {
        return service.getOrdersWithFullInfo();
    }

    @PostMapping("/create")
    public OrderDTO createOrder(@RequestBody OrderCreateDTO createDTO) throws UserPrincipalNotFoundException {
        return service.createOrder(createDTO);
    }

    @PostMapping("/create/orders-list")
    public List<OrderDTO> createOrder(@RequestBody List<OrderCreateDTO> createDTO) throws UserPrincipalNotFoundException {
        return service.createOrder(createDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}
