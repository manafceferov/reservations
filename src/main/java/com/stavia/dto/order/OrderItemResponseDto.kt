package com.stavia.dto.order

open class OrderItemResponseDto @JvmOverloads constructor(
    open var menuItemName: String? = null,
    open var quantity: Int? = null,
    open var unitPrice: Double? = null
)