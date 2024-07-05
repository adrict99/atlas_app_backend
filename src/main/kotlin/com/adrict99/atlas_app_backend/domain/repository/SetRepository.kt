package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Set
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SetRepository: JpaRepository<Set, Long> {

    fun findAllSetsByExerciseId(id: Long): List<Set>

    fun findSetById(id: Long): Set

    fun existsSetById(id: Long): Boolean

}