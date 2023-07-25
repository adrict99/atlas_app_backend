package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.presentation.dto.SetDTO
import com.adrict99.atlas_app_backend.presentation.request.set.SetCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.set.SetUpdateRequest
import com.adrict99.atlas_app_backend.application.service.SetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/sets")
class SetController(private val service: SetService) {

    @PostMapping
    fun createSet(
            @Valid @RequestBody request: SetCreateRequest
    ): ResponseEntity<SetDTO> = ResponseEntity(service.createSet(request), HttpStatus.OK)

    @GetMapping("/{exerciseId}")
    fun getAllSetsByExerciseId(@PathVariable exerciseId: Long): ResponseEntity<List<SetDTO>> =
            ResponseEntity(service.findAllSetsByExerciseId(exerciseId), HttpStatus.OK)

    @GetMapping("/{setId}")
    fun getSetById(@PathVariable setId: Long): ResponseEntity<SetDTO> =
            ResponseEntity(service.findSetById(setId), HttpStatus.OK)

    @PatchMapping("/{setId}")
    fun updateSet(
        @PathVariable setId: Long,
        @Valid @RequestBody request: SetUpdateRequest
    ): ResponseEntity<SetDTO> = ResponseEntity(service.updateSet(setId, request), HttpStatus.OK)

    @DeleteMapping("/{setId}")
    fun deleteSet(@PathVariable setId: Long) = ResponseEntity(service.deleteSetById(setId), HttpStatus.OK)

}