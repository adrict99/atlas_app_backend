package com.adrict99.atlas_app_backend.util.extensions

import com.adrict99.atlas_app_backend.presentation.dto.SetDTO
import com.adrict99.atlas_app_backend.domain.entity.Set

fun SetDTO.toSet() = Set(
    id = this.id,
    weight = this.weight,
    repetitions = this.repetitions,
    rir = this.rir,
    exercise = this.exercise.toExercise()
)

fun List<SetDTO>.toSetMutableList(): MutableList<Set> = map { it.toSet() }.toMutableList()

fun Set.toSetDTO() = SetDTO(
    id = this.id,
    weight = this.weight,
    repetitions = this.repetitions,
    rir = this.rir,
    exercise = this.exercise.toExerciseDTO()
)

fun List<Set>.toSetDTOMutableList(): MutableList<SetDTO> = map { it.toSetDTO() }.toMutableList()
