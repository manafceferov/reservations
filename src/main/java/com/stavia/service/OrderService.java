package com.stavia.service;

import com.stavia.dto.order.OrderCreateDto;
import com.stavia.dto.order.OrderResponseDto;
import com.stavia.entity.MenuItem;
import com.stavia.entity.Order;
import com.stavia.entity.OrderItem;
import com.stavia.entity.TableReservation;
import com.stavia.exception.ResourceNotFoundException;
import com.stavia.mapper.OrderMapper;
import com.stavia.repository.OrderRepository;
import com.stavia.repository.TableReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableReservationRepository tableReservationRepository;
    private final MenuItemService menuItemService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        TableReservationRepository tableReservationRepository,
                        MenuItemService menuItemService,
                        OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.tableReservationRepository = tableReservationRepository;
        this.menuItemService = menuItemService;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDto create(OrderCreateDto dto) {
        TableReservation tableReservation = tableReservationRepository.findById(dto.getTableReservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Masa rezervasiyası tapılmadı"));

        Order order = new Order();
        order.setTableReservation(tableReservation);

        double totalPrice = 0.0;
        for (var itemDto : dto.getItems()) {
            MenuItem menuItem = menuItemService.findById(itemDto.getMenuItemId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setUnitPrice(menuItem.getPrice());
            order.getItems().add(orderItem);
            totalPrice += menuItem.getPrice() * itemDto.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    public Page<OrderResponseDto> getUserOrders(Long userId, Pageable pageable) {
        return orderRepository.findAllByTableReservationUserIdAndDeletedFalse(userId, pageable)
                .map(orderMapper::toResponseDto);
    }

    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAllByDeletedFalse(pageable)
                .map(orderMapper::toResponseDto);
    }
}