package com.stavia.dto.menuitem

open class MenuItemResponseDto @JvmOverloads constructor(
    open var id: Long? = null,
    open var name: String? = null,
    open var description: String? = null,
    open var price: Double? = null,
    open var category: String? = null,
    open var available: Boolean? = null
)