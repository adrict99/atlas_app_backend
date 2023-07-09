package com.adrict99.atlas_app_backend.application.service

import com.adrict99.atlas_app_backend.domain.entity.*
import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.presentation.dto.SetDTO
import com.adrict99.atlas_app_backend.presentation.request.set.SetCreateRequest
import com.adrict99.atlas_app_backend.presentation.request.set.SetUpdateRequest
import com.adrict99.atlas_app_backend.presentation.exception.set.SetNotFoundException
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.*
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrNull
import kotlin.reflect.full.memberProperties

@Service
class SetService(private val repository: SetRepository) {

    private fun mapEntityToDTO(set: Set) = SetDTO(
        weight = set.weight,
        id = set.id,
        repetitions = set.repetitions,
        rir = set.rir
    )

    private fun mapRequestToEntity(set: Set, request: SetCreateRequest) {
        set.weight = request.weight
        set.repetitions = request.repetitions
        set.rir = request.rir
    }

    fun findSetById(id: Long): SetDTO {
        setExistsById(id)
        val set: Set = repository.findSetById(id)
        return mapEntityToDTO(set)
    }

    fun findAllSets(): List<SetDTO> =
            repository.findAllSets().stream().map(this::mapEntityToDTO).collect(Collectors.toList())

    fun deleteSetById(id: Long) {
        setExistsById(id)
        repository.deleteSetById(id)
    }

    fun deleteAllSets() = repository.deleteAllSets()

    private fun setExistsById(id: Long) {
        if (!repository.existsSetById(id)) throw SetNotFoundException("Set with the ID $id does not exist")
    }

    fun createSet(request: SetCreateRequest): SetDTO {
        val user = User()
        val routine = Routine(user = user)
        val workout = Workout(routine = routine)
        val exercise = Exercise( workout = workout)
        val set = Set(exercise = exercise)
        mapRequestToEntity(set, request)
        val savedSet = repository.saveSet(set)
        return mapEntityToDTO(savedSet)
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
        val savedSet = repository.saveSet(existingSet)
        return mapEntityToDTO(savedSet)
    }

}