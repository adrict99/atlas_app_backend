package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.RoutineService
import com.adrict99.atlas_app_backend.presentation.dto.RoutineDTO
import com.adrict99.atlas_app_backend.presentation.request.routine.RoutineCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.routine.RoutineUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/routines")
class RoutineController(private val service: RoutineService) {

    @PostMapping
    fun createRoutine(
            @Valid @RequestBody request: RoutineCreateRequest
    ): ResponseEntity<RoutineDTO> = ResponseEntity(service.createRoutine(request), HttpStatus.OK)

    @GetMapping("/{userId}")
    fun getAllRoutinesByUserId(@PathVariable userId: Long): ResponseEntity<List<RoutineDTO>> =
            ResponseEntity(service.findAllRoutinesByUserId(userId), HttpStatus.OK)

    @GetMapping("/{routineId}")
    fun getRoutineById(@PathVariable routineId: Long): ResponseEntity<RoutineDTO> =
            ResponseEntity(service.findRoutineById(routineId), HttpStatus.OK)

    @PatchMapping("/{routineId}")
    fun updateRoutine(
            @PathVariable routineId: Long,
            @RequestBody request: RoutineUpdateRequest
    ): ResponseEntity<RoutineDTO> = ResponseEntity(service.updateRoutine(routineId, request), HttpStatus.OK)

    @DeleteMapping("/{routineId}")
    fun deleteRoutine(@PathVariable routineId: Long) = ResponseEntity(service.deleteRoutineById(routineId), HttpStatus.OK)

}