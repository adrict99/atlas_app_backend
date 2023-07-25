package com.adrict99.atlas_app_backend.presentation.request.workout

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class WorkoutCreateRequest(
    @Size(max = 20, message = "Workout name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Workout name can only contain letters and numbers"
    )
    val name: String?,

    @NotNull
    val routineId: Long,

    @Size(max = 100, message = "Workout notes must contain a maximum of 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Workout notes can only contain letters and numbers"
    )
    val notes: String?
)
