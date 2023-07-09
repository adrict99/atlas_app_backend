package com.adrict99.atlas_app_backend.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
data class Routine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank
    @Size(max = 20, message = "Routine name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Routine name can only contain letters and numbers"
    )
    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "routine", cascade = [CascadeType.ALL], orphanRemoval = true)
    val workouts: MutableList<Workout> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    val user: User,

    @Size(max = 100, message = "Routine description must contain a maximum of 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Routine description can only contain letters and numbers"
    )
    @Column(nullable = true)
    var description: String? = null
)
