package com.adrict99.atlas_app_backend.presentation.request.user

import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserCreateRequest(
    @Size(max = 20, message = "User name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[A-Za-z '-]+\$",
        message = "User name can only contain letters and compound names characters"
    )
    val name: String?,

    @Email
    @Size(max = 64, message = "Email name must contain a maximum of 64 characters")
    val email: String?,

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&+=])(?=\\S+\$).{8,}\$",
        message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace"
    )
    val password: String?
)
