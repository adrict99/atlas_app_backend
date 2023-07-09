package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Set
import org.springframework.stereotype.Repository

@Repository
interface SetRepository {

    fun saveSet(set: Set): Set

    fun saveAllSets(sets: List<Set>): List<Set>

    fun findSetById(id: Long): Set

    fun findAllSets(): List<Set>

    fun updateSet(set: Set): Set

    fun deleteSetById(id: Long): Boolean

    fun deleteAllSets(): Boolean

    fun existsRoutineById(id: Long): Boolean

}