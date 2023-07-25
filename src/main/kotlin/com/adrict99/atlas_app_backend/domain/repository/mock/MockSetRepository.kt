package com.adrict99.atlas_app_backend.domain.repository.mock

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import org.springframework.stereotype.Repository

@Repository
class MockSetRepository: SetRepository {
    override fun saveSet(set: Set): Set {
        TODO("Not yet implemented")
    }

    override fun findAllSetsByExerciseId(id: Long): List<Set> {
        TODO("Not yet implemented")
    }

    override fun findSetById(id: Long): Set {
        TODO("Not yet implemented")
    }

    override fun updateSet(set: Set): Set {
        TODO("Not yet implemented")
    }

    override fun deleteSetById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun existsSetById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}