package com.stavia.controller;

import com.stavia.config.ApiResponse;
import com.stavia.dto.order.OrderCreateDto;
import com.stavia.dto.order.OrderResponseDto;
import com.stavia.enums.Messages;
import com.stavia.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<OrderResponseDto> create(@RequestBody OrderCreateDto dto) {
        return new ApiResponse<>(true, orderService.create(dto), Messages.CREATED.name());
    }

    @GetMapping("/my")
    public ApiResponse<Page<OrderResponseDto>> myOrders(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, orderService.getUserOrders(userId, PageRequest.of(page, size)), Messages.SUCCESS.name());
    }

    @GetMapping
    public ApiResponse<Page<OrderResponseDto>> allOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ApiResponse<>(true, orderService.getAllOrders(PageRequest.of(page, size)), Messages.SUCCESS.name());
    }
}