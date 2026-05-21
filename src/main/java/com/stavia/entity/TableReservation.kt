package com.stavia.entity

import com.stavia.enums.ReservationStatus
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "table_reservations")
open class TableReservation @JvmOverloads constructor(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    open var table: RestaurantTable? = null,

    @Column(name = "reservation_date", nullable = false)
    open var reservationDate: LocalDate? = null,

    @Column(name = "reservation_time", nullable = false)
    open var reservationTime: LocalTime? = null,

    @Column(name = "guest_count", nullable = false)
    open var guestCount: Int = 1,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: ReservationStatus = ReservationStatus.PENDING,

    @Column(name = "notes", columnDefinition = "TEXT")
    open var notes: String? = null,

    // ——— YENİ ƏLAVƏ OLUNAN MANY TO MANY ƏLAQƏSİ ———
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "reservation_menu_items",
        joinColumns = [JoinColumn(name = "reservation_id")],
        inverseJoinColumns = [JoinColumn(name = "menu_item_id")]
    )
    open var menuItems: MutableList<MenuItem> = arrayListOf()

) : BaseEntity()