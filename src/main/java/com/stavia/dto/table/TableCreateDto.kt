package com.stavia.dto.table

open class TableCreateDto @JvmOverloads constructor(
    open var tableNumber: Int? = null,
    open var capacity: Int? = null,
    open var location: String? = null
)