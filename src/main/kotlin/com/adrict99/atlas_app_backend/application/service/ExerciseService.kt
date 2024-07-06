package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import com.adrict99.atlas_app_backend.domain.repository.ExerciseRepository
import com.adrict99.atlas_app_backend.presentation.dto.ExerciseDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseUpdateRequest
import com.adrict99.atlas_app_backend.util.extensions.toExerciseDTO
import com.adrict99.atlas_app_backend.util.extensions.toExerciseDTOMutableList
import com.adrict99.atlas_app_backend.util.extensions.toWorkout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

@Service
class ExerciseService(
        private val repository: ExerciseRepository,
        private val workoutService: WorkoutService
) {

    fun createExercise(request: ExerciseCreateRequest): ExerciseDTO {
        val workoutDTO = workoutService.findWorkoutById(request.workoutId)
        val exercise = Exercise(
                name = request.name,
                repRange = request.repRange,
                targetNumberOfSets = request.targetNumberOfSets,
                workout = workoutDTO.toWorkout()
        )
        return repository.save(exercise).toExerciseDTO()
    }

    fun findAllExercisesByWorkoutId(workoutId: Long): MutableList<ExerciseDTO> {
        workoutService.findWorkoutById(workoutId)
        return repository.findAllExercisesByWorkoutId(workoutId).toExerciseDTOMutableList()
    }

    fun findExerciseById(exerciseId: Long): ExerciseDTO {
        repository.existsExerciseById(exerciseId)
        return repository.findExerciseById(exerciseId).toExerciseDTO()
    }

    fun updateExercise(id: Long, request: ExerciseUpdateRequest): ExerciseDTO {
        exerciseExistsById(id)
        val existingExercise = repository.findExerciseById(id)

        for (property in ExerciseUpdateRequest::class.memberProperties) {
            if (property.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(Exercise::class.java, property.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingExercise, property.get(request))
                }
            }
        }
        return repository.save(existingExercise).toExerciseDTO()
    }

    fun deleteExerciseById(exerciseId: Long) {
        exerciseExistsById(exerciseId)
        repository.deleteById(exerciseId)
    }

    private fun exerciseExistsById(exerciseId: Long) {
        if (!repository.existsExerciseById(exerciseId)) throw EntityNotFoundException("Exercise with the ID $exerciseId does not exist")
    }

}