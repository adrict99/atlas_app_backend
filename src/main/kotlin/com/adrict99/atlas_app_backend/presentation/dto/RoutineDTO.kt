package com.adrict99.atlas_app_backend.presentation.dto

data class RoutineDTO(
    val id: Long? = null,
    val name: String? = null,
    val user: UserDTO? = null,
    val workouts: MutableList<WorkoutDTO>? = null,
    val description: String? = null,
    val notes: String? = null
)
