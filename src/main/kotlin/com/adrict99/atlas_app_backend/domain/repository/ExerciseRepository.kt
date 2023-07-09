package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import com.adrict99.atlas_app_backend.domain.entity.Workout
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository {

    fun saveExercise(exercise: Exercise): Exercise

    fun saveAllExercises(exercises: List<Exercise>): List<Exercise>

    fun findAllExercisesByWorkoutId(id: Long): List<Exercise>

    fun findExerciseById(exerciseId: Long): Exercise

    fun updateExercise(exercise: Exercise): Exercise

    fun deleteExerciseById(exerciseId: Long): Boolean

    fun existsExerciseById(exerciseId: Long): Boolean

}