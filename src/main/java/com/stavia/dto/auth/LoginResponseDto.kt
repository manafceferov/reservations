package com.stavia.dto.auth

open class LoginResponseDto @JvmOverloads constructor(
    open var firstName: String? = null,
    open var lastName: String? = null,
    open var token: String? = null,
    open var userId: Long? = null,
    open var email: String? = null,
    open var role: String? = null
)