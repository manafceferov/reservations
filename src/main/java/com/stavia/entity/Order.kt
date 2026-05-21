package com.stavia.entity

import com.stavia.enums.ReservationStatus
import jakarta.persistence.*

@Entity
@Table(name = "orders")
open class Order @JvmOverloads constructor(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_id", nullable = false)
    open var tableReservation: TableReservation? = null,

    @Column(name = "total_price", nullable = false)
    open var totalPrice: Double = 0.0,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: ReservationStatus = ReservationStatus.PENDING,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var items: MutableList<OrderItem> = mutableListOf()

) : BaseEntity()