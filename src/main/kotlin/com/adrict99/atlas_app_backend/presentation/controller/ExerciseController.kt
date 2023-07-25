package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.ExerciseService
import com.adrict99.atlas_app_backend.presentation.dto.ExerciseDTO
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/exercises")
class ExerciseController(private val service: ExerciseService) {

    @PostMapping
    fun createExercise(
        @Valid @RequestBody request: ExerciseCreateRequest
    ): ResponseEntity<ExerciseDTO> = ResponseEntity(service.createExercise(request), HttpStatus.OK)

    @GetMapping("/{workoutId}")
    fun getAllExercisesByWorkoutId(@PathVariable workoutId: Long): ResponseEntity<List<ExerciseDTO>> =
        ResponseEntity(service.findAllExercisesByWorkoutId(workoutId), HttpStatus.OK)

    @GetMapping("/{exerciseId}")
    fun getExerciseById(@PathVariable exerciseId: Long): ResponseEntity<ExerciseDTO> =
            ResponseEntity(service.findExerciseById(exerciseId), HttpStatus.OK)

    @PatchMapping("/{exerciseId}")
    fun updateExercise(
            @PathVariable exerciseId: Long,
            @RequestBody request: ExerciseUpdateRequest
    ): ResponseEntity<ExerciseDTO> = ResponseEntity(service.updateExercise(exerciseId, request), HttpStatus.OK)

    @DeleteMapping("/{exerciseId}")
    fun deleteExercise(@PathVariable exerciseId: Long) = ResponseEntity(service.deleteExerciseById(exerciseId), HttpStatus.OK)

}