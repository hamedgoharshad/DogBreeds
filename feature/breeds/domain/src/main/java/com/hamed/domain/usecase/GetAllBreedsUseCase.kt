package com.hamed.domain.usecase

import androidx.annotation.VisibleForTesting
import com.hamed.common.domain.di.IoDispatcher
import com.hamed.common.domain.baseUsecase.CoroutineUseCase
import com.hamed.domain.model.Breed
import com.hamed.domain.repository.BreedsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Breed>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<Breed> =
        breedsRepository.getAllBreeds().run {
            if (!isEmpty()) this else throw emptyListException
        }

    companion object {
        const val EMPTY_LIST_MSG = "There is nothing here"
        val emptyListException = Exception(EMPTY_LIST_MSG)
    }
}

