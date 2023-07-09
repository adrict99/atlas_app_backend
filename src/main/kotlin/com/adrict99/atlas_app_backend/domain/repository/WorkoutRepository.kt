package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Workout
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository {

    fun saveWorkout(workout: Workout): Workout

    fun saveAllWorkouts(workouts: List<Workout>): List<Workout>

    fun findAllWorkoutsByRoutineId(id: Long): List<Workout>

    fun findWorkoutById(id: Long): Workout

    fun updateWorkout(workout: Workout): Workout

    fun deleteWorkoutById(id: Long): Boolean

    fun existsWorkoutById(id: Long): Boolean

}