package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import com.adrict99.atlas_app_backend.domain.entity.Workout
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository {

    fun saveWorkout(workout: Workout): Workout

    fun findAllWorkoutsByRoutineId(routineId: Long): List<Workout>

    fun findWorkoutById(workoutId: Long): Workout

    fun updateWorkout(workout: Workout): Workout

    fun updateWorkoutNameById(workoutId: Long, workoutName: String): Workout

    fun updateWorkoutExercisesById(workoutId: Long, newExercises: List<Exercise>): Workout

    fun updateWorkoutNotesById(workoutId: Long, newNotes: String?): Workout

    fun deleteWorkoutById(workoutId: Long): Boolean

    fun existsWorkoutById(workoutId: Long): Boolean

}