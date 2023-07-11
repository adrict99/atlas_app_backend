package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.Exercise
import com.adrict99.atlas_app_backend.domain.repository.ExerciseRepository
import com.adrict99.atlas_app_backend.presentation.dto.ExerciseDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseUpdateRequest
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class ExerciseService(private val repository: ExerciseRepository) {

    //TODO: Check what to do with sets & workout
    private fun mapEntityToDTO(exercise: Exercise) = ExerciseDTO(
        id = exercise.id,
        name = exercise.name,
        repRange = exercise.repRange,
        targetNumberOfSets = exercise.targetNumberOfSets,
        //sets = exercise.sets,
        //workout = exercise.workout,
        notes = exercise.notes
    )

    private fun mapRequestToEntity(exercise: Exercise, request: ExerciseCreateRequest) {
        exercise.name = request.name
        exercise.repRange = request.repRange
        exercise.targetNumberOfSets = request.targetNumberOfSets
        exercise.notes = request.notes
    }

    //TODO: Check what to do with exercise dependency
    fun saveExercise(request: ExerciseCreateRequest): ExerciseDTO {
        val exercise = Exercise()
        mapRequestToEntity(exercise, request = request)
        val newExercise = repository.saveExercise(exercise = exercise)
        return mapEntityToDTO(newExercise)
    }

    //TODO: Check what to do with exercise dependency
    fun saveAllExercises(requests: List<ExerciseCreateRequest>): List<ExerciseDTO> {
        val exercisesList = listOf<Exercise>()
        requests.map {
            mapRequestToEntity(exercise = Exercise(), request = it)
        }
        val newExercises = repository.saveAllExercises(exercisesList)
        return newExercises.map { mapEntityToDTO(it) }
    }

    fun findAllExercisesByWorkoutId(id: Long): List<ExerciseDTO> =
        repository.findAllExercisesByWorkoutId(id).stream().map(this::mapEntityToDTO).collect(Collectors.toList())

    fun findExerciseById(id: Long): ExerciseDTO {
        exerciseExistsById(id)
        val exercise: Exercise = repository.findExerciseById(id)
        return mapEntityToDTO(exercise)
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
        val savedSet = repository.saveExercise(existingExercise)
        return mapEntityToDTO(savedSet)
    }

    private fun exerciseExistsById(id: Long) {
        if (!repository.existsExerciseById(id)) throw EntityNotFoundException("Exercise with the ID $id does not exist")
    }

}