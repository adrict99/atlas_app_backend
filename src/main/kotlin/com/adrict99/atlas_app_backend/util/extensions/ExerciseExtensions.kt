package com.adrict99.atlas_app_backend.util.extensions

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import com.adrict99.atlas_app_backend.presentation.dto.ExerciseDTO

fun ExerciseDTO.toExercise() = Exercise(
    id = this.id,
    name = this.name,
    repRange = this.repRange,
    targetNumberOfSets = this.targetNumberOfSets,
    sets = this.sets?.toSetMutableList() ?: mutableListOf(),
    workout = this.workout.toWorkout(),
    notes = this.notes
)

fun List<ExerciseDTO>.toExerciseMutableList(): MutableList<Exercise> = map { it.toExercise() }.toMutableList()

fun Exercise.toExerciseDTO() = ExerciseDTO(
    id = this.id,
    name = this.name,
    repRange = this.repRange,
    targetNumberOfSets = this.targetNumberOfSets,
    sets = this.sets.toSetDTOMutableList(),
    workout = this.workout.toWorkoutDTO(),
    notes = this.notes
)

fun List<Exercise>.toExerciseDTOMutableList(): MutableList<ExerciseDTO> = map { it.toExerciseDTO() }.toMutableList()
