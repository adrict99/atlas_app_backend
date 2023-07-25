package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import com.adrict99.atlas_app_backend.presentation.dto.SetDTO
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.presentation.request.set.SetCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.set.SetUpdateRequest
import com.adrict99.atlas_app_backend.util.extensions.toExercise
import com.adrict99.atlas_app_backend.util.extensions.toSetDTO
import com.adrict99.atlas_app_backend.util.extensions.toSetDTOMutableList
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties

@Service
class SetService(
        private val repository: SetRepository,
        private val exerciseService: ExerciseService
) {

    fun createSet(request: SetCreateRequest): SetDTO {
        val exerciseDTO = exerciseService.findExerciseById(request.exerciseId)
        val set = Set(
                weight = request.weight,
                repetitions = request.repetitions,
                rir = request.rir,
                exercise = exerciseDTO.toExercise()
        )
        return repository.saveSet(set).toSetDTO()
    }

    fun findAllSetsByExerciseId(exerciseId: Long): MutableList<SetDTO> {
        exerciseService.findExerciseById(exerciseId)
        return repository.findAllSetsByExerciseId(exerciseId).toSetDTOMutableList()
    }

    fun findSetById(setId: Long): SetDTO {
        repository.existsSetById(setId)
        return repository.findSetById(setId).toSetDTO()
    }

    fun updateSet(id: Long, request: SetUpdateRequest): SetDTO {
        setExistsById(id)
        val existingSet = repository.findSetById(id)

        for (property in SetUpdateRequest::class.memberProperties) {
            if (property.get(request) != null) {
                val field: Field? = ReflectionUtils.findField(Set::class.java, property.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingSet, property.get(request))
                }
            }
        }
        return repository.updateSet(existingSet).toSetDTO()
    }

    fun deleteSetById(setId: Long) {
        setExistsById(setId)
        repository.deleteSetById(setId)
    }

    private fun setExistsById(setId: Long) {
        if (!repository.existsSetById(setId)) throw EntityNotFoundException("Set with the ID $setId does not exist")
    }

}