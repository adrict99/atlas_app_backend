package com.adrict99.atlas_app_backend.repository.mock

import com.adrict99.atlas_app_backend.domain.entity.Set
import com.adrict99.atlas_app_backend.domain.repository.mock.MockSetRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockSetRepositoryTest {

    private val mockSetRepository = MockSetRepository()

    @Test
    fun `Given a set when saved then is stored correctly`() {
        //Given
        val set = Set().apply {
            this.weight = 85.0
            this.repetitions = 8
            this.rir = 2
        }
        //When
        val savedSet = mockSetRepository.saveSet(set)
        //Then
        assertNotNull(savedSet.id)
    }

}