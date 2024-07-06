package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository : JpaRepository<Workout, Long> {

    fun findAllWorkoutsByRoutineId(id: Long): List<Workout>

    fun findWorkoutById(id: Long): Workout

    fun existsWorkoutById(id: Long): Boolean

}