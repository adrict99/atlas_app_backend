package com.adrict99.atlas_app_backend.domain.entity

import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
data class Set(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @DecimalMin(value = "0.1", inclusive = false, message = "Weight must be greater than 0.0")
    @Column(nullable = false)
    var weight: Double? = null,

    @Min(value = 1, message = "Repetitions must be greater than 0")
    @Column(nullable = false)
    var repetitions: Int? = null,

    @Max(value = 10)
    @Min(value = 0)
    @Column(nullable = true)
    var rir: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    @NotNull
    val exercise: Exercise,
)
