package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.Workout
import com.adrict99.atlas_app_backend.domain.repository.WorkoutRepository
import com.adrict99.atlas_app_backend.presentation.dto.WorkoutDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.workout.WorkoutCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.workout.WorkoutUpdateRequest
import com.adrict99.atlas_app_backend.util.extensions.toRoutine
import com.adrict99.atlas_app_backend.util.extensions.toWorkoutDTO
import com.adrict99.atlas_app_backend.util.extensions.toWorkoutDTOMutableList
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

@Service
class WorkoutService(
        private val repository: WorkoutRepository,
        private val routineService: RoutineService
) {

    fun createWorkout(request: WorkoutCreateRequest): WorkoutDTO {
        val routineDTO = routineService.findRoutineById(request.routineId)
        val workout = Workout(name = request.name, routine = routineDTO.toRoutine(), notes = request.notes)
        return repository.saveWorkout(workout).toWorkoutDTO()
    }

    fun findAllWorkoutsByRoutineId(routineId: Long): MutableList<WorkoutDTO> {
        routineService.findRoutineById(routineId)
        return repository.findAllWorkoutsByRoutineId(routineId).toWorkoutDTOMutableList()
    }

    fun findWorkoutById(workoutId: Long): WorkoutDTO {
        repository.existsWorkoutById(workoutId)
        return repository.findWorkoutById(workoutId).toWorkoutDTO()
    }

    fun updateWorkout(workoutId: Long, request: WorkoutUpdateRequest): WorkoutDTO {
        workoutExistsById(workoutId)
        val existingWorkout = repository.findWorkoutById(workoutId)

        for (property in WorkoutUpdateRequest::class.memberProperties) {
            if (property.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(Workout::class.java, property.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingWorkout, property.get(request))
                }
            }
        }
        return repository.updateWorkout(existingWorkout).toWorkoutDTO()
    }

    fun deleteWorkoutById(workoutId: Long) {
        workoutExistsById(workoutId)
        repository.deleteWorkoutById(workoutId)
    }

    private fun workoutExistsById(workoutId: Long) {
        if (!repository.existsWorkoutById(workoutId)) throw EntityNotFoundException("Workout with the ID $workoutId does not exist")
    }

}