package com.adrict99.atlas_app_backend.util.extensions

import com.adrict99.atlas_app_backend.domain.entity.Workout
import com.adrict99.atlas_app_backend.presentation.dto.WorkoutDTO

fun WorkoutDTO.toWorkout() = Workout(
    id = this.id,
    name = this.name,
    exercises = this.exercises?.toExerciseMutableList() ?: mutableListOf(),
    routine = this.routine.toRoutine(),
    notes = this.notes
)

fun List<WorkoutDTO>.toWorkoutMutableList(): MutableList<Workout> = map { it.toWorkout() }.toMutableList()

fun Workout.toWorkoutDTO() = WorkoutDTO(
    id = this.id,
    name = this.name,
    exercises = this.exercises.toExerciseDTOMutableList(),
    routine = this.routine.toRoutineDTO(),
    notes = this.notes
)

fun List<Workout>.toWorkoutDTOMutableList(): MutableList<WorkoutDTO> = map { it.toWorkoutDTO() }.toMutableList()
