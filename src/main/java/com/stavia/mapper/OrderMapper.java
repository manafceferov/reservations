package com.stavia.mapper;

import com.stavia.dto.order.OrderItemResponseDto;
import com.stavia.dto.order.OrderResponseDto;
import com.stavia.entity.Order;
import com.stavia.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    @Mapping(target = "tableReservationId", source = "tableReservation.id")
    @Mapping(target = "status", expression = "java(order.getStatus().name())")
    @Mapping(target = "createdAt", expression = "java(order.getCreatedAt() != null ? order.getCreatedAt().toString() : null)")
    OrderResponseDto toResponseDto(Order order);

    @Mapping(target = "menuItemName", source = "menuItem.name")
    OrderItemResponseDto toItemResponseDto(OrderItem item);
}