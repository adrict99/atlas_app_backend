package com.adrict99.atlas_app_backend.repository

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.mock.MockSetRepository
import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.Test

class SetRepositoryTest {

    private val mockSetRepository = MockSetRepository()

    @Test
    fun `Given a set when saved then store correctly`() {
        //Given
        val set = Set(weight = 100.0, repetitions = 3, rir = 0)
        //When
        val savedSet = mockSetRepository.saveSet(set)
        //Then
        assertNotNull(savedSet.id)
    }

    @Test
    fun `When all sets are requested then they are retrieved correctly`() {
        //When
        val allSets = mockSetRepository.findAllSets()
        //Then
        assertNotNull(allSets.map { it.id })
    }

}