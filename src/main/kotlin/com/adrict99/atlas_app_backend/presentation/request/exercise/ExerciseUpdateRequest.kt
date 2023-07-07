package com.adrict99.atlas_app_backend.presentation.request.exercise

import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ExerciseUpdateRequest(
    @Size(max = 20, message = "Exercise name must contain a maximum of 20 characters")
    val name: String?,

    @Pattern(regexp = "^(\\d+)-(\\d+)$", message = "Exercise repetitions range must follow the pattern 'integer-integer'")
    val repRange: String?,

    @Min(value = 1, message = "Exercise number of target sets must be greater than 0")
    val targetNumberOfSets: Int?,

    val notes: String? = null
)
