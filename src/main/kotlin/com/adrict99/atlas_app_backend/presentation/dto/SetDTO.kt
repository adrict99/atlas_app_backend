package com.adrict99.atlas_app_backend.presentation.dto

data class SetDTO(
    val id: Long? = null,
    val weight: Double? = null,
    val repetitions: Int? = null,
    val rir: Int? = null,
    val exercise: ExerciseDTO
)