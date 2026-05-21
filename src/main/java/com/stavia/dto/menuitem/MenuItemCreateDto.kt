package com.stavia.dto.menuitem

open class MenuItemCreateDto @JvmOverloads constructor(
    open var name: String? = null,
    open var description: String? = null,
    open var price: Double? = null,
    open var category: String? = null
)