package com.stavia.entity

import com.stavia.enums.RoomType
import jakarta.persistence.*
import org.hibernate.annotations.Nationalized

@Entity
@Table(name = "rooms")
open class Room @JvmOverloads constructor(

    @Column(name = "room_number", nullable = false, unique = true, length = 20)
    open var roomNumber: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    open var roomType: RoomType? = null,

    @Column(name = "price_per_night", nullable = false)
    open var pricePerNight: Double? = null,

    @Column(name = "capacity", nullable = false)
    open var capacity: Int? = null,

    @Nationalized
    @Column(name = "description", columnDefinition = "TEXT")
    open var description: String? = null,

    @Column(name = "available", nullable = false)
    open var available: Boolean = true,

    @OneToMany(mappedBy = "room", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var reservations: MutableList<RoomReservation> = mutableListOf()

) : BaseEntity()