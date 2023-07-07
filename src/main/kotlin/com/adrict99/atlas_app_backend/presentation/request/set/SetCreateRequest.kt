package com.adrict99.atlas_app_backend.presentation.request.set

import javax.validation.constraints.*

data class SetCreateRequest(
    @DecimalMin(
        value = "0.1",
        inclusive = false,
        message = "Weight must be greater than 0.0"
    ) val weight: Double? = null,

    @Min(value = 1, message = "Repetitions must be greater than 0") val repetitions: Int? = null,

    @Max(value = 10) @Min(value = 0) val rir: Int? = null,

    val exerciseId: Long? = null
)
