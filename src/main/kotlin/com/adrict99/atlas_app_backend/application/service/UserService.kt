package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

}