package com.hamed.presentation.allBreeds

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.hamed.common.testshared.TestDispatcherRule
import com.hamed.domain.model.Breed
import com.hamed.domain.repository.BreedsRepository
import com.hamed.domain.usecase.GetAllBreedsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class BreedsViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val breedsRepository = mockk<BreedsRepository>()
    private lateinit var getAllBreedsUseCase: GetAllBreedsUseCase
    private lateinit var viewModel: BreedsViewModel

    @Before
    fun setup() = runTest {
        getAllBreedsUseCase =
            GetAllBreedsUseCase(breedsRepository, StandardTestDispatcher(testScheduler))
        viewModel = BreedsViewModel(getAllBreedsUseCase)
    }

    @Test
    fun `when collect uiState gets successful result`() = runTest {
        coEvery { breedsRepository.getAllBreeds() } returns testBreeds
        viewModel.breedsUiState.test {
            awaitItem()
            val state: BreedsUiState = awaitItem()
            coVerify { breedsRepository.getAllBreeds() }
            assertTrue(state is BreedsUiState.Success)
            assertEquals(testBreeds, (state as BreedsUiState.Success).breeds)
        }
    }

    @Test
    fun `uiState when initialized shows Loading`() = runTest {
        viewModel.breedsUiState.test {
            assertEquals(BreedsUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `when collect uiState gets failed result`() = runTest {
        coEvery { breedsRepository.getAllBreeds() } throws testException
        viewModel.breedsUiState.test {
            awaitItem()
            val state = awaitItem()
            assertEquals(testException.message, (state as BreedsUiState.Failed).exception.message)
            advanceUntilIdle()
        }
    }

    private val testBreeds = listOf(Breed("test breed"))
    private val testException = Exception("test exception")
}