package com.stavia.dto.table

open class TableReservationCreateDto @JvmOverloads constructor(
    open var tableId: Long? = null,
    open var reservationDate: String? = null,
    open var reservationTime: String? = null,
    open var guestCount: Int = 1,
    open var notes: String? = null,
    open var menuItemIds: List<Long> = ArrayList()
)