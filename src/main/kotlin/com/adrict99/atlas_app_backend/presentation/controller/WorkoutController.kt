package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.WorkoutService
import com.adrict99.atlas_app_backend.presentation.dto.WorkoutDTO
import com.adrict99.atlas_app_backend.presentation.request.workout.WorkoutCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.workout.WorkoutUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/workouts")
class WorkoutController(private val service: WorkoutService) {

    @PostMapping
    fun createWorkout(
            @Valid @RequestBody request: WorkoutCreateRequest
    ): ResponseEntity<WorkoutDTO> = ResponseEntity(service.createWorkout(request), HttpStatus.OK)

    @GetMapping("/{routineId}")
    fun getAllWorkoutsByRoutineId(@PathVariable routineId: Long): ResponseEntity<List<WorkoutDTO>> =
            ResponseEntity(service.findAllWorkoutsByRoutineId(routineId), HttpStatus.OK)

    @GetMapping("/{workoutId}")
    fun getWorkoutById(@PathVariable workoutId: Long): ResponseEntity<WorkoutDTO> =
            ResponseEntity(service.findWorkoutById(workoutId), HttpStatus.OK)

    @PatchMapping("/{workoutId}")
    fun updateWorkout(
            @PathVariable workoutId: Long,
            @RequestBody request: WorkoutUpdateRequest
    ): ResponseEntity<WorkoutDTO> = ResponseEntity(service.updateWorkout(workoutId, request), HttpStatus.OK)

    @DeleteMapping("/{workoutId}")
    fun deleteWorkout(@PathVariable workoutId: Long) = ResponseEntity(service.deleteWorkoutById(workoutId), HttpStatus.OK)

}