package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Routine
import com.adrict99.atlas_app_backend.domain.entity.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun saveUser(user: User): User

    fun findUserById(id: Long): User

    fun findUserByEmail(email: String): User

    fun updateUser(user: User): User

    fun deleteUserById(id: Long): Boolean

    fun existsUserById(id: Long): Boolean

}