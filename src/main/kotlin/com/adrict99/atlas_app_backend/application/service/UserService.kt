package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.User
import com.adrict99.atlas_app_backend.domain.repository.UserRepository
import com.adrict99.atlas_app_backend.presentation.dto.UserDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.user.UserCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.user.UserUpdateRequest
import com.adrict99.atlas_app_backend.util.extensions.toUserDTO
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

@Service
class UserService(private val repository: UserRepository) {

    fun createUser(request: UserCreateRequest): UserDTO {
        val user = User(
            name = request.name,
            email = request.email,
            password = request.password
        )
        val newUser = repository.saveUser(user)
        return newUser.toUserDTO()
    }

    fun findUserById(id: Long): UserDTO {
        userExistsById(id)
        return repository.findUserById(id).toUserDTO()
    }

    fun updateUser(id: Long, request: UserUpdateRequest): UserDTO {
        userExistsById(id)
        val existingUser = repository.findUserById(id)

        for (property in UserUpdateRequest::class.memberProperties) {
            if (property.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(User::class.java, property.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingUser, property.get(request))
                }
            }
        }
        return repository.updateUser(existingUser).toUserDTO()
    }

    fun deleteUserById(id: Long) {
        userExistsById(id)
        repository.deleteUserById(id)
    }

    private fun userExistsById(id: Long) {
        if (!repository.existsUserById(id)) throw EntityNotFoundException("User with the ID $id does not exist")
    }

}