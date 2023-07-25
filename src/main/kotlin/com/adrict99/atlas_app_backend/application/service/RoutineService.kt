package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.Routine
import com.adrict99.atlas_app_backend.domain.repository.RoutineRepository
import com.adrict99.atlas_app_backend.presentation.dto.RoutineDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.routine.RoutineCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.routine.RoutineUpdateRequest
import com.adrict99.atlas_app_backend.util.extensions.toRoutineDTO
import com.adrict99.atlas_app_backend.util.extensions.toRoutineDTOMutableList
import com.adrict99.atlas_app_backend.util.extensions.toUser
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

@Service
class RoutineService(
    private val repository: RoutineRepository,
    private val userService: UserService
) {

    fun createRoutine(request: RoutineCreateRequest): RoutineDTO {
        val userDTO = userService.findUserById(request.userId)
        val routine = Routine(name = request.name, user = userDTO.toUser(), description = request.description)
        return repository.saveRoutine(routine).toRoutineDTO()
    }

    fun findAllRoutinesByUserId(userId: Long): MutableList<RoutineDTO> {
        userService.findUserById(userId)
        return repository.findAllRoutinesByUserId(userId).toRoutineDTOMutableList()
    }

    fun findRoutineById(routineId: Long): RoutineDTO {
        repository.existsRoutineById(routineId)
        return repository.findRoutineById(routineId).toRoutineDTO()
    }

    fun updateRoutine(routineId: Long, request: RoutineUpdateRequest): RoutineDTO {
        routineExistsById(routineId)
        val existingRoutine = repository.findRoutineById(routineId)

        for (property in RoutineUpdateRequest::class.memberProperties) {
            if (property.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(Routine::class.java, property.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingRoutine, property.get(request))
                }
            }
        }
        return repository.updateRoutine(existingRoutine).toRoutineDTO()
    }

    fun deleteRoutineById(routineId: Long) {
        routineExistsById(routineId)
        repository.deleteRoutineById(routineId)
    }

    private fun routineExistsById(id: Long) {
        if (!repository.existsRoutineById(id)) throw EntityNotFoundException("Routine with the ID $id does not exist")
    }

}