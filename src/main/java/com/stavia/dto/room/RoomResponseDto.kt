package com.stavia.dto.room

open class RoomResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var roomNumber: String? = null,
    open var roomType: String? = null,
    open var pricePerNight: Double? = null,
    open var capacity: Int? = null,
    open var description: String? = null,
    open var available: Boolean? = null
)