package com.stavia.entity

import jakarta.persistence.*

@Entity
@Table(name = "order_items")
open class OrderItem @JvmOverloads constructor(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    open var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    open var menuItem: MenuItem? = null,

    @Column(name = "quantity", nullable = false)
    open var quantity: Int = 1,

    @Column(name = "unit_price", nullable = false)
    open var unitPrice: Double? = null

) : BaseEntity()