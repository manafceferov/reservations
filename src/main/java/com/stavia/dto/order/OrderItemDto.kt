package com.stavia.dto.order

open class OrderItemDto @JvmOverloads constructor(
    open var menuItemId: Long? = null,
    open var quantity: Int = 1
)