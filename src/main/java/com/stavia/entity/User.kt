package com.stavia.entity

import com.stavia.enums.Role
import jakarta.persistence.*
import org.hibernate.annotations.Nationalized

@Entity
@Table(name = "users")
open class User @JvmOverloads constructor(

    @Nationalized
    @Column(name = "first_name", nullable = false, length = 100)
    open var firstName: String? = null,

    @Nationalized
    @Column(name = "last_name", nullable = false, length = 100)
    open var lastName: String? = null,

    @Column(name = "email", nullable = false, unique = true, length = 100)
    open var email: String? = null,

    @Column(name = "password", nullable = false)
    open var password: String? = null,

    @Column(name = "phone_number", length = 20)
    open var phoneNumber: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    open var role: Role = Role.GUEST,

    @Column(name = "active", nullable = false)
    open var active: Boolean = true,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var roomReservations: MutableList<RoomReservation> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var tableReservations: MutableList<TableReservation> = mutableListOf()

) : BaseEntity()