package com.adrict99.atlas_app_backend.domain.entity

import javax.persistence.*
import javax.validation.constraints.*

@Entity
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotBlank
    @Size(max = 20, message = "Exercise name must contain a maximum of 20 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Exercise name can only contain letters and numbers"
    )
    @Column(nullable = false)
    var name: String? = null,

    @NotBlank
    @Pattern(
        regexp = "^(\\d+)-(\\d+)$",
        message = "Exercise repetitions range must follow the pattern 'integer-integer'"
    )
    @Column(nullable = false)
    var repRange: String? = null,

    @NotBlank
    @Min(value = 1, message = "Exercise number of target sets must be greater than 0")
    @Column(nullable = false)
    var targetNumberOfSets: Int? = null,

    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sets: MutableList<Set> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    @NotNull
    val workout: Workout,

    @Size(max = 100, message = "Exercise notes must contain a maximum of 100 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9]+\$",
        message = "Exercise notes can only contain letters and numbers"
    )
    @Column(nullable = true)
    var notes: String? = null
)