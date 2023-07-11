package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.ExerciseService
import com.adrict99.atlas_app_backend.presentation.dto.ExerciseDTO
import com.adrict99.atlas_app_backend.presentation.request.exercise.ExerciseCreateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api")
class ExerciseController(private val service: ExerciseService) {

    @PostMapping("save")
    fun saveExercise(
        @Valid @RequestBody request: ExerciseCreateRequest
    ): ResponseEntity<ExerciseDTO> = ResponseEntity(service.saveExercise(request), HttpStatus.OK)

    @PostMapping("save-all")
    fun saveExercises(
        @Valid @RequestBody requests: List<ExerciseCreateRequest>
    ): ResponseEntity<List<ExerciseDTO>> = ResponseEntity(service.saveAllExercises(requests), HttpStatus.OK)

    @GetMapping("exercise/{id}")
    fun getAllExercisesByWorkoutId(@PathVariable id: Long): ResponseEntity<List<ExerciseDTO>> =
        ResponseEntity(service.findAllExercisesByWorkoutId(id = id), HttpStatus.OK)

}