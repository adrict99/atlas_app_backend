package com.adrict99.atlas_app_backend.presentation.request.routine

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class RoutineCreateRequest(
    @Size(max = 20, message = "Routine name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Routine name can only contain letters and numbers"
    )
    val name: String?,

    @NotNull
    val userId: Long,

    @Size(max = 100, message = "Routine description must contain a maximum of 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Routine description can only contain letters and numbers"
    )
    val description: String?
)
