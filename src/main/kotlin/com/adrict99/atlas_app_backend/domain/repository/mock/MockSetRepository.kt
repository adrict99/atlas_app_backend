package com.adrict99.atlas_app_backend.domain.repository.mock

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MockSetRepository: SetRepository {
    override fun saveSet(set: Set): Set = set

    override fun saveAllSets(sets: List<Set>): List<Set> = sets

    override fun findSetById(id: Long): Optional<Set> {
        TODO("Not yet implemented")
    }

    override fun findAllSets(): List<Set> {
        TODO("Not yet implemented")
    }

    override fun deleteSetById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteAllSets(): Boolean {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }


}