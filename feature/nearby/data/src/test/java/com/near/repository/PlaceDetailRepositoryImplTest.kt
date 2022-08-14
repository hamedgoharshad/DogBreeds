package com.near.repository

import com.near.common.testshared.CoroutineRule
import com.near.datasource.local.interfaces.LocalPlaceDetailDataSource
import com.google.common.truth.Truth
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaceDetailRepositoryImplTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    @MockK
    private lateinit var mockLocalPlaceDetailDataSource: LocalPlaceDetailDataSource

    @MockK
    private lateinit var mockRemotePlaceDetailDatasource: RemoteBreedsDatasource

    private lateinit var placeDetailRepositoryImpl: BreedsRepositoryImpl

    @Before
    fun setup() {
        placeDetailRepositoryImpl = BreedsRepositoryImpl(
            mockRemotePlaceDetailDatasource
        )
    }

    @Test
    fun `when cache is empty then request api`() = runTest {
        coEvery { mockLocalPlaceDetailDataSource.getPlaceDetail() } returns null

        placeDetailRepositoryImpl.getAllBreeds()

        coVerify { mockRemotePlaceDetailDatasource.getAllBreeds() }
    }

    @Test
    fun `getPlaceDetail fetches placeDetailEntity and maps to PlaceDetail`() {
        val id = "id"
        coEvery { mockRemotePlaceDetailDatasource.getPlaceDetail(id) } returns FakeData.fakePlaceDetailResponse
        coEvery { mockLocalPlaceDetailDataSource.getPlaceDetail(id) } returns FakeData.fakePlaceDetailResponse.body()?.map()

        val result = runBlocking { placeDetailRepositoryImpl.getPlaceDetail(id) }

        Truth.assertThat(result.fsqId).isEqualTo(id)
        coVerify { mockLocalPlaceDetailDataSource.getPlaceDetail(id) }
    }

    @Test
    fun `getPlaceDetail when cache is empty requests api`() {
        val id = "id"
        coEvery { mockRemotePlaceDetailDatasource.getPlaceDetail(id) } returns FakeData.fakePlaceDetailResponse
        coEvery { mockLocalPlaceDetailDataSource.getPlaceDetail(id) } returns null

        val result = runBlocking { placeDetailRepositoryImpl.getPlaceDetail(id) }

        coVerify { mockRemotePlaceDetailDatasource.getPlaceDetail(id) }
        Truth.assertThat(result.fsqId).isEqualTo(id)
    }
}