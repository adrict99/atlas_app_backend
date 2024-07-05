package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long>{

    fun findAllExercisesByWorkoutId(id: Long): List<Exercise>

    fun findExerciseById(exerciseId: Long): Exercise

    fun existsExerciseById(exerciseId: Long): Boolean

}