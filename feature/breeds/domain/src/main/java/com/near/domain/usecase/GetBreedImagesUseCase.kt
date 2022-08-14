package com.near.domain.usecase

import com.near.common.domain.di.IoDispatcher
import com.near.common.domain.usecase.CoroutineUseCase
import com.near.domain.repository.BreedsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBreedImagesUseCase @Inject constructor(
    private val breedsRepository: BreedsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, List<String>>(ioDispatcher) {
    override suspend fun execute(parameters: String): List<String> =
        breedsRepository.getImages(parameters).run {
            if (!isEmpty()) this else throw Exception("There is nothing here")
        }
}