package com.stavia.entity

import jakarta.persistence.*
import org.hibernate.annotations.Nationalized

@Entity
@Table(name = "menu_items")
open class MenuItem @JvmOverloads constructor(

    @Nationalized
    @Column(name = "name", nullable = false, length = 150)
    open var name: String? = null,

    @Nationalized
    @Column(name = "description", columnDefinition = "TEXT")
    open var description: String? = null,

    @Column(name = "price", nullable = false)
    open var price: Double? = null,

    @Column(name = "category", length = 100)
    open var category: String? = null,

    @Column(name = "available", nullable = false)
    open var available: Boolean = true

) : BaseEntity()