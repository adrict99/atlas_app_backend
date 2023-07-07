package com.adrict99.atlas_app_backend.domain.repository

import com.adrict99.atlas_app_backend.domain.entity.Routine
import com.adrict99.atlas_app_backend.domain.entity.User
import org.springframework.stereotype.Repository

@Repository
interface RoutineRepository {

    fun saveRoutine(routine: Routine): Routine

    fun findAllRoutinesByUserId(userId: Long): List<Routine>

    fun findRoutineById(routineId: Long): Routine

    fun updateRoutine(routine: Routine): Routine

    fun deleteRoutineById(routineId: Long): Boolean

    fun existsRoutineById(routineId: Long): Boolean

}