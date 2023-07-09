package com.adrict99.atlas_app_backend.domain.repository.mock

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MockSetRepository: SetRepository {
    override fun saveSet(set: Set): Set {
        TODO("Not yet implemented")
    }

    override fun saveAllSets(sets: List<Set>): List<Set> {
        TODO("Not yet implemented")
    }

    override fun findSetById(id: Long): Set {
        TODO("Not yet implemented")
    }

    override fun findAllSets(): List<Set> {
        TODO("Not yet implemented")
    }

    override fun updateSet(set: Set): Set {
        TODO("Not yet implemented")
    }

    override fun deleteSetById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteAllSets(): Boolean {
        TODO("Not yet implemented")
    }

    override fun existsSetById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}