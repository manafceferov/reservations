package com.stavia.dto.order

open class OrderCreateDto @JvmOverloads constructor(
    open var tableReservationId: Long? = null,
    open var items: MutableList<OrderItemDto> = mutableListOf()
)