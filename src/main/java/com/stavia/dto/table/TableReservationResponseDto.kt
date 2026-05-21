package com.stavia.dto.table

import com.stavia.dto.menuitem.MenuItemResponseDto

open class TableReservationResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var tableNumber: Int? = null,
    open var reservationDate: String? = null,
    open var reservationTime: String? = null,
    open var guestCount: Int? = null,
    open var status: String? = null,
    open var notes: String? = null,
    open var userEmail: String? = null,
    open var createdAt: String? = null,
    open var menuItems: List<MenuItemResponseDto> = ArrayList()
)