package com.adrict99.atlas_app_backend.domain.entity

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @NotBlank
        @Size(max = 20, message = "User name must contain a maximum of 20 characters")
        @Pattern(
                regexp = "^[A-Za-z '-]+\$",
                message = "User name can only contain letters and compound names characters"
        )
        @Column(nullable = false)
        var name: String? = null,

        @NotBlank
        @Email
        @Size(max = 64, message = "Email name must contain a maximum of 64 characters")
        @Column(nullable = false)
        var email: String? = null,

        @NotBlank
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&+=])(?=\\S+\$).{8,}\$",
                message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace"
        )
        @Column(nullable = false)
        var password: String? = null,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
        val routines: MutableList<Routine> = mutableListOf()
)