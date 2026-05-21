package com.stavia.dto.table

open class TableResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var tableNumber: Int? = null,
    open var capacity: Int? = null,
    open var location: String? = null,
    open var available: Boolean? = null
)