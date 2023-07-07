package com.adrict99.atlas_app_backend.presentation.dto

data class UserDTO(
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val routines: MutableList<RoutineDTO>? = null
)
