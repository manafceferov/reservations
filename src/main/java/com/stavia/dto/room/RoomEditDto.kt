package com.stavia.dto.room

open class RoomEditDto @JvmOverloads constructor(
    open var pricePerNight: Double? = null,
    open var capacity: Int? = null,
    open var description: String? = null,
    open var available: Boolean? = null
)