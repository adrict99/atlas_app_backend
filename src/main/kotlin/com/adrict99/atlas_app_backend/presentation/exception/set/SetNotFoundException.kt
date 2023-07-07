package com.adrict99.atlas_app_backend.presentation.exception.set

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class SetNotFoundException(override val message: String) : RuntimeException()
