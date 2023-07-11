package com.adrict99.atlas_app_backend.presentation.controller

import com.adrict99.atlas_app_backend.application.service.RoutineService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class RoutineController(private val service: RoutineService) {

}