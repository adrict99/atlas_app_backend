package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Set
import org.springframework.stereotype.Repository

@Repository
interface SetRepository {

    fun saveSet(set: Set): Set

    fun findAllSetsByExerciseId(id: Long): List<Set>

    fun findSetById(id: Long): Set

    fun updateSet(set: Set): Set

    fun deleteSetById(id: Long): Boolean

    fun existsSetById(id: Long): Boolean

}