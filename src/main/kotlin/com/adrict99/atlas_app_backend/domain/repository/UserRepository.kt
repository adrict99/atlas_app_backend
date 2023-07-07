package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository {

    fun saveUser(user: User): User

    fun findUserById(userId: Long): User

    fun findUserByEmail(userEmail: String): User

    fun updateUser(user: User): User

    fun deleteUserById(id: Long): Boolean

    fun existsUserById(id: Long): Boolean

}