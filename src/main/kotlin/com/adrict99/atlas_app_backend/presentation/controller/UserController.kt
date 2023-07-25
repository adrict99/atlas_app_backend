package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.UserService
import com.adrict99.atlas_app_backend.presentation.dto.UserDTO
import com.adrict99.atlas_app_backend.presentation.request.user.UserCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.user.UserUpdateRequest
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
@RequestMapping("api/users")
class UserController(private val service: UserService) {

    @PostMapping
    fun createUser(
        @Valid @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserDTO> = ResponseEntity(service.createUser(request), HttpStatus.OK)

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDTO> =
        ResponseEntity(service.findUserById(userId), HttpStatus.OK)

    @PatchMapping("/{userId}")
    fun updateUser(
        @PathVariable userId: Long,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserDTO> = ResponseEntity(service.updateUser(userId, request), HttpStatus.OK)

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long) = ResponseEntity(service.deleteUserById(userId), HttpStatus.OK)

}