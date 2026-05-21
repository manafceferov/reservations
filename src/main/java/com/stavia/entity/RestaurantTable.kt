package com.stavia.entity

import jakarta.persistence.*

@Entity
@Table(name = "restaurant_tables")
open class RestaurantTable @JvmOverloads constructor(

    @Column(name = "table_number", nullable = false, unique = true)
    open var tableNumber: Int? = null,

    @Column(name = "capacity", nullable = false)
    open var capacity: Int? = null,

    @Column(name = "location", length = 100)
    open var location: String? = null,

    @Column(name = "available", nullable = false)
    open var available: Boolean = true,

    @OneToMany(mappedBy = "table", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var reservations: MutableList<TableReservation> = mutableListOf()

) : BaseEntity()