package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Routine
import org.springframework.stereotype.Repository

@Repository
interface RoutineRepository {

    fun saveRoutine(routine: Routine): Routine

    fun findAllRoutinesByUserId(id: Long): List<Routine>

    fun findRoutineById(id: Long): Routine

    fun updateRoutine(routine: Routine): Routine

    fun deleteRoutineById(id: Long): Boolean

    fun existsRoutineById(id: Long): Boolean

}