package com.stavia.dto.room

open class RoomReservationCreateDto @JvmOverloads constructor(
    open var roomId: Long? = null,
    open var checkIn: String? = null,
    open var checkOut: String? = null,
    open var guestCount: Int = 1,
    open var notes: String? = null
)