package com.hamed.domain.usecase

import com.hamed.common.domain.di.IoDispatcher
import com.hamed.common.domain.usecase.CoroutineUseCase
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
            if (!isEmpty()) this else throw Exception("There is nothing here")
        }
}