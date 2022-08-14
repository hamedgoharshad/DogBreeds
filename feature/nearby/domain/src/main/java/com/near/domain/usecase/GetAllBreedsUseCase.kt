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
) : CoroutineUseCase<Unit, Result<List<Breed>>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): Result<List<Breed>> =
        breedsRepository.getAllBreeds()
}