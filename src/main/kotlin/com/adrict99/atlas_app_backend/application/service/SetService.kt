package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.*
import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.presentation.dto.SetDTO
import com.adrict99.atlas_app_backend.presentation.request.set.SetCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.set.SetUpdateRequest
import com.adrict99.atlas_app_backend.presentation.exception.EntityNotFoundException
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class SetService(private val repository: SetRepository) {

    private fun mapEntityToDTO(set: Set) = SetDTO(
        weight = set.weight,
        id = set.id,
        repetitions = set.repetitions,
        rir = set.rir
    )

    private fun Set.toDTO() = SetDTO(
        weight = this.weight,
        id = this.id,
        repetitions = this.repetitions,
        rir = this.rir
    )

    private fun mapRequestToEntity(set: Set, request: SetCreateRequest) {
        set.weight = request.weight
        set.repetitions = request.repetitions
        set.rir = request.rir
    }

    fun findSetById(id: Long): SetDTO {
        setExistsById(id)
        return repository.findSetById(id).toDTO()
    }

    //TODO: Check if I can change this mapEntityToDTO to toDTO extension function
    fun findAllSets(): List<SetDTO> =
            repository.findAllSets().stream().map(this::mapEntityToDTO).collect(Collectors.toList())

    fun deleteSetById(id: Long) {
        setExistsById(id)
        repository.deleteSetById(id)
    }

    fun deleteAllSets() = repository.deleteAllSets()

    private fun setExistsById(id: Long) {
        if (!repository.existsSetById(id)) throw EntityNotFoundException("Set with the ID $id does not exist")
    }

    fun createSet(request: SetCreateRequest): SetDTO {
        val user = User()
        val routine = Routine(user = user)
        val workout = Workout(routine = routine)
        val exercise = Exercise( workout = workout)
        val set = Set(exercise = exercise)
        mapRequestToEntity(set, request)
        return repository.saveSet(set).toDTO()
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
        return repository.saveSet(existingSet).toDTO()
    }

}