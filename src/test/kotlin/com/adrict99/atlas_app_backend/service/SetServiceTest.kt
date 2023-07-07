package com.adrict99.atlas_app_backend.service

import com.adrict99.atlas_app_backend.application.service.SetService
import com.adrict99.atlas_app_backend.domain.repository.SetRepository
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SetServiceTest {

    private val setRepository: SetRepository = mockk()
    private val setService = SetService(setRepository)

    @Test
    fun `Given  when  then `() {
    //Given

    //When

    //Then

    }

}