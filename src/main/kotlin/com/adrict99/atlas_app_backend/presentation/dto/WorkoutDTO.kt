package com.adrict99.atlas_app_backend.presentation.dto

data class WorkoutDTO(
    val id: Long? = null,
    val name: String? = null,
    val exercises: MutableList<ExerciseDTO>? = null,
    val routine: RoutineDTO,
    val notes: String? = null
)