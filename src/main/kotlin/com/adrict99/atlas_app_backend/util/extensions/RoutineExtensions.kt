package com.adrict99.atlas_app_backend.util.extensions

import com.adrict99.atlas_app_backend.domain.entity.Routine
import com.adrict99.atlas_app_backend.presentation.dto.RoutineDTO

fun RoutineDTO.toRoutine() = Routine(
    id = this.id,
    name = this.name,
    workouts = this.workouts?.toWorkoutMutableList() ?: mutableListOf(),
    user = this.user.toUser(),
    description = this.description
)

fun List<RoutineDTO>.toRoutineMutableList(): MutableList<Routine> = map { it.toRoutine() }.toMutableList()

fun Routine.toRoutineDTO() = RoutineDTO(
    id = this.id,
    name = this.name,
    workouts = this.workouts.toWorkoutDTOMutableList(),
    user = this.user.toUserDTO(),
    description = this.description
)

fun List<Routine>.toRoutineDTOMutableList(): MutableList<RoutineDTO> = map { it.toRoutineDTO() }.toMutableList()
