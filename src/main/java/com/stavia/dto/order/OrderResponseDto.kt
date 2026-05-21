package com.stavia.dto.order

open class OrderResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var tableReservationId: Long? = null,
    open var totalPrice: Double? = null,
    open var status: String? = null,
    open var items: MutableList<OrderItemResponseDto> = mutableListOf(),
    open var createdAt: String? = null
)