package com.stavia.dto.room

open class RoomCreateDto @JvmOverloads constructor(
    open var roomNumber: String? = null,
    open var roomType: String? = null,
    open var pricePerNight: Double? = null,
    open var capacity: Int? = null,
    open var description: String? = null
)