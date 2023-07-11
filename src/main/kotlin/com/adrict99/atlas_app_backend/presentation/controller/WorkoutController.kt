package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.WorkoutService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class WorkoutController(private val service: WorkoutService) {

}