package com.stavia.dto.user

open class UserRegisterDto @JvmOverloads constructor(
    open var firstName: String? = null,
    open var lastName: String? = null,
    open var email: String? = null,
    open var password: String? = null,
    open var phoneNumber: String? = null
)