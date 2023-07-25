package com.adrict99.atlas_app_backend.presentation.dto

data class ExerciseDTO(
    val id: Long? = null,
    val name: String? = null,
    val repRange: String? = null,
    val targetNumberOfSets: Int? = null,
    val sets: MutableList<SetDTO>? = null,
    val workout: WorkoutDTO,
    val notes: String? = null
)