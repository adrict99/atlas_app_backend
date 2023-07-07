package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Set
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SetRepository {

    fun saveSet(set: Set): Set

    fun saveAllSets(sets: List<Set>): List<Set>

    fun findSetById(id: Long): Set

    fun findAllSets(): List<Set>

    fun deleteSetById(id: Long): Boolean

    fun deleteAllSets(): Boolean

    fun existsById(id: Long): Boolean

}