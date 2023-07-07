package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Workout
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository {

    fun saveWorkout(workout: Workout): Workout

    fun findAllWorkoutsByRoutineId(routineId: Long): List<Workout>

    fun findWorkoutById(workoutId: Long): Workout

    fun updateWorkout(workout: Workout): Workout

    fun deleteWorkoutById(workoutId: Long): Boolean

    fun existsWorkoutById(workoutId: Long): Boolean

}