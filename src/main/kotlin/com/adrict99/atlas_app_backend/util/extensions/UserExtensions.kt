package com.adrict99.atlas_app_backend.util.extensions

import com.adrict99.atlas_app_backend.domain.entity.User
import com.adrict99.atlas_app_backend.presentation.dto.UserDTO

fun UserDTO.toUser() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    password = this.password,
    routines = this.routines?.toRoutineMutableList() ?: mutableListOf()
)

fun User.toUserDTO() = UserDTO(
    id = this.id,
    name = this.name,
    email = this.email,
    password = this.password,
    routines = this.routines.toRoutineDTOMutableList()
)
