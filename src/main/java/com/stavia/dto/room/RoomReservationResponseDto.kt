package com.stavia.dto.room

open class RoomReservationResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var roomNumber: String? = null,
    open var roomType: String? = null,
    open var checkIn: String? = null,
    open var checkOut: String? = null,
    open var totalPrice: Double? = null,
    open var guestCount: Int? = null,
    open var status: String? = null,
    open var notes: String? = null,
    open var userEmail: String? = null,
    open var createdAt: String? = null
)