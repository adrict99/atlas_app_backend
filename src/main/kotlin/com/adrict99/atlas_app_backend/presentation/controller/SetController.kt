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
@RequestMapping("api")
class SetController(private val service: SetService) {

    @GetMapping("all-sets")
    fun getAllSets(): ResponseEntity<List<SetDTO>> = ResponseEntity(service.findAllSets(), HttpStatus.OK)

    @GetMapping("set/{id}")
    fun getSetById(@PathVariable id: Long): ResponseEntity<SetDTO> =
            ResponseEntity(service.findSetById(id), HttpStatus.OK)

    @PostMapping("create")
    fun createSet(
        @Valid @RequestBody request: SetCreateRequest
    ): ResponseEntity<SetDTO> = ResponseEntity(service.createSet(request), HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateSet(
        @PathVariable id: Long,
        @Valid @RequestBody request: SetUpdateRequest
    ): ResponseEntity<SetDTO> = ResponseEntity(service.updateSet(id, request), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteSet(@PathVariable id: Long) = ResponseEntity(service.deleteSetById(id), HttpStatus.OK)

    @DeleteMapping("delete-all")
    fun deleteAllSets() = ResponseEntity(service.deleteAllSets(), HttpStatus.OK)

}