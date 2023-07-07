package com.adrict99.atlas_app_backend.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
data class Workout(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank
    @Size(max = 20, message = "Workout name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Workout name can only contain letters and numbers"
    )
    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "workout", cascade = [CascadeType.ALL], orphanRemoval = true)
    var exercises: MutableList<Exercise> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    @NotNull
    val routine: Routine,

    @Size(max = 100, message = "Workout notes must contain a maximum of 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Workout notes can only contain letters and numbers"
    )
    @Column(nullable = true)
    var notes: String? = null
)
