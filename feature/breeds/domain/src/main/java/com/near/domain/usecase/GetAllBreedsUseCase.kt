package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.usecase.CoroutineUseCase
import com.near.common.domain.utils.Result
import com.near.domain.model.Breed
import com.near.domain.repository.BreedsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, List<Breed>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<Breed> =
        breedsRepository.getAllBreeds().run {
            if (isEmpty()) this else throw Exception("There is nothing here")
        }
}