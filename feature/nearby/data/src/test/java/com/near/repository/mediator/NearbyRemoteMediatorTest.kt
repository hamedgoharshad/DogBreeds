package com.near.repository.mediator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.near.common.data.persistent.database.NearbyDatabase
import com.near.datasource.local.LocalNearbyDatasourceImpl
import com.near.datasource.local.LocalRemoteKeyDatasourceImpl
import com.near.datasource.local.interfaces.LocalNearbyDatasource
import com.near.datasource.local.interfaces.RemoteKeyDatasource
import com.near.datasource.remote.interfaces.RemoteBreedsDatasource
import com.near.repository.locationFixtures
import com.near.webApi.response.NearbyResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class NearbyRemoteMediatorTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var nearbyRemoteMediator: NearbyRemoteMediator
    private val standardTestDispatcher = StandardTestDispatcher()

    private val remoteBreedsDatasource = mockk<RemoteBreedsDatasource>(relaxed = true)
    lateinit var remoteKeyDatasource: RemoteKeyDatasource
    lateinit var localNearbyDatasource: LocalNearbyDatasource
    lateinit var nearbyDatabase: NearbyDatabase

    @Before
    fun config() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        nearbyDatabase = Room.inMemoryDatabaseBuilder(
            context, nearbyDatabase::class.java
        ).allowMainThreadQueries().build()

        remoteKeyDatasource = LocalRemoteKeyDatasourceImpl(
            nearbyDatabase.getRemoteKeyDao()
        )

        localNearbyDatasource = LocalNearbyDatasourceImpl(
            nearbyDatabase.getPlaceDao(), nearbyDatabase
        )

        nearbyRemoteMediator = NearbyRemoteMediator(
            remoteKeyDatasource, remoteBreedsDatasource, localNearbyDatasource
        )
    }

    @Test
    fun `check PREPEND state when location is null`() = runTest(standardTestDispatcher) {
        val response = nearbyRemoteMediator.load(
            LoadType.PREPEND, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(response is RemoteMediator.MediatorResult.Success)
        assertTrue((response as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `check PREPEND state and location is not null`() = runTest(standardTestDispatcher) {
        val response = nearbyRemoteMediator(NearbyRemoteMediator.Param(locationFixtures,"")).load(
            LoadType.PREPEND, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(response is RemoteMediator.MediatorResult.Success)
        assertTrue((response as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `load when locationEntity is null in REFRESH state`() = runTest(standardTestDispatcher) {
        coEvery { remoteBreedsDatasource.getAllBreeds(any()) } coAnswers {
            Response.success(null)
        }
        val response = nearbyRemoteMediator.load(
            LoadType.REFRESH, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(response is RemoteMediator.MediatorResult.Error)
        assertTrue(
            (response as RemoteMediator.MediatorResult.Error).throwable is UninitializedPropertyAccessException
        )
    }

    @Test
    fun `load when locationEntity is null in APPEND state`() = runTest(standardTestDispatcher) {
        coEvery { remoteBreedsDatasource.getAllBreeds(any()) } coAnswers {
            Response.success(null)
        }
        val response = nearbyRemoteMediator.load(
            LoadType.APPEND, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(response is RemoteMediator.MediatorResult.Error)
        assertTrue(
            (response as RemoteMediator.MediatorResult.Error).throwable is UninitializedPropertyAccessException
        )
    }

    @Test
    fun `check REFRESH state return null from api`() = runTest(standardTestDispatcher) {
        val response = Response.success(null)
        coEvery { remoteBreedsDatasource.getAllBreeds(any()) } coAnswers {
            response as Response<NearbyResponse>
        }

        val repoResponse = nearbyRemoteMediator(NearbyRemoteMediator.Param(locationFixtures,"")).load(
            LoadType.REFRESH, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(repoResponse is RemoteMediator.MediatorResult.Error)
        assertEquals(
            response.message(),
            (repoResponse as RemoteMediator.MediatorResult.Error).throwable.message
        )
    }

    @Test
    fun `check REFRESH state return error from api`() = runTest(standardTestDispatcher) {
        val response = Response.error<Exception>(
            404, nearbyRemoteMediatorTestFixture.responseBody
        )
        coEvery { remoteBreedsDatasource.getAllBreeds(any()) } coAnswers {
            response as Response<NearbyResponse>
        }

        val repoResponse = nearbyRemoteMediator(NearbyRemoteMediator.Param(locationFixtures,"")).load(
            LoadType.REFRESH, PagingState(
                listOf(),
                null,
                nearbyRemoteMediatorTestFixture.pageConfig,
                nearbyRemoteMediatorTestFixture.leadingPlaceholderCount
            )
        )

        assertTrue(repoResponse is RemoteMediator.MediatorResult.Error)
        assertEquals(
            response.message(),
            (repoResponse as RemoteMediator.MediatorResult.Error).throwable.message
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        nearbyDatabase.close()
    }

    object nearbyRemoteMediatorTestFixture {
        val pageConfig: PagingConfig by lazy {
            PagingConfig(pageSize = 20, initialLoadSize = 20)
        }
        const val leadingPlaceholderCount = 20
        val responseBody = "{\"key\":[\"somestuff\"]}"
            .toResponseBody("application/json".toMediaTypeOrNull())
        val serverResponse = Response.success(NearbyResponse(emptyList()))
    }
}
