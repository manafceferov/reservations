package com.stavia.entity

import com.stavia.enums.ReservationStatus
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "room_reservations")
open class RoomReservation @JvmOverloads constructor(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    open var room: Room? = null,

    @Column(name = "check_in", nullable = false)
    open var checkIn: LocalDate? = null,

    @Column(name = "check_out", nullable = false)
    open var checkOut: LocalDate? = null,

    @Column(name = "total_price", nullable = false)
    open var totalPrice: Double? = null,

    @Column(name = "guest_count", nullable = false)
    open var guestCount: Int = 1,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: ReservationStatus = ReservationStatus.PENDING,

    @Column(name = "notes", columnDefinition = "TEXT")
    open var notes: String? = null

) : BaseEntity()