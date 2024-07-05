package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Routine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoutineRepository : JpaRepository<Routine, Long>{


    fun findAllRoutinesByUserId(id: Long): List<Routine>

    fun findRoutineById(id: Long): Routine

    fun existsRoutineById(id: Long): Boolean

}