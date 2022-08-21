package com.hamed.domain.usecase

import androidx.test.filters.SmallTest
import com.hamed.common.domain.utils.Result
import com.hamed.common.domain.utils.error
import com.hamed.common.testshared.TestDispatcherRule
import com.hamed.domain.model.Breed
import com.hamed.domain.repository.BreedsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class GetAllBreedsUseCaseTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val breedsRepository = mockk<BreedsRepository>()
    private lateinit var getAllBreedsUseCase: GetAllBreedsUseCase

    @Before
    fun setup() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        getAllBreedsUseCase = GetAllBreedsUseCase(
            breedsRepository, dispatcher
        )
    }

    @Test
    fun `empty list returns failure`() = runTest {
        coEvery { breedsRepository.getAllBreeds() } returns emptyList()
        val result = getAllBreedsUseCase(Unit)
        val msg = result.error?.message
        assertEquals(
            msg,
            GetAllBreedsUseCase.EMPTY_LIST_MSG
        )
    }

    @Test
    fun `successful fetch returns result`() = runTest {
        val fakeResult = listOf(Breed("fake name"))
        coEvery { breedsRepository.getAllBreeds() } returns fakeResult
        val result = getAllBreedsUseCase(Unit)
        coVerify { breedsRepository.getAllBreeds() }
        assertEquals(
            result,
            Result.Success(fakeResult)
        )
    }
}