package com.near.repository

import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.near.datasource.local.interfaces.LocationDatasource
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocationRepositoryImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var locationRepository: LocationRepository
    private lateinit var fakeDataSource: SuccessLocationDataSource
    private val standardTestDispatcher = StandardTestDispatcher()

    @Before
    fun config() {
        MockKAnnotations.init(this)
        locationRepository = LocationRepositoryImpl(SuccessLocationDataSource())
    }

    @Test
    fun `save location successful`() = runTest(standardTestDispatcher) {
        locationRepository.saveLastLocation(LocationRepositoryImplTestFixture.firstSimpleLocation)
        fakeDataSource.savedLocation.collectLatest {
            assert(
                it == LocationRepositoryImplTestFixture.firstLocationEntity
            )
        }
    }

    @Test
    fun `load location return success`() = runTest(standardTestDispatcher) {
        fakeDataSource.saveLastLocation(
            LocationRepositoryImplTestFixture.firstLocationEntity
        )
        locationRepository.savedLocation.collectLatest {
            assert(it == LocationRepositoryImplTestFixture.firstSimpleLocation)
        }
    }

    @Test
    fun `load Repository returns Latest Location Success`() = runTest(standardTestDispatcher) {
        fakeDataSource.saveLastLocation(
            LocationRepositoryImplTestFixture.firstLocationEntity
        )
        fakeDataSource.saveLastLocation(
            LocationRepositoryImplTestFixture.secondLocationEntity
        )
        assert(
            locationRepository.savedLocation.first() == LocationRepositoryImplTestFixture.secondSimpleLocation
        )
    }

    @Test
    fun `invalid value doesn't be saved`() = runTest(standardTestDispatcher) {
        locationRepository.saveLastLocation(LocationRepositoryImplTestFixture.secondSimpleLocation)
        locationRepository.saveLastLocation(null)
        fakeDataSource.savedLocation.collectLatest {
            assert(
                it == LocationRepositoryImplTestFixture.secondLocationEntity
            )
        }
    }

    @Test
    fun `load location return failure`() = runTest(standardTestDispatcher) {
        fakeDataSource.saveLastLocation(
            LocationRepositoryImplTestFixture.firstLocationEntity
        )
        locationRepository.savedLocation.collectLatest {
            assert(it == LocationRepositoryImplTestFixture.firstSimpleLocation)
        }
    }
}

class SuccessLocationDataSource : LocationDatasource {
    private var fakeSavedLocation = Location("")

    override val savedLocation: Flow<Location>
        get() = flowOf(fakeSavedLocation)

    override fun getFreshLocation(): Flow<Location> =
        flowOf(Location(""))

    override suspend fun saveLastLocation(location: Location) {
        fakeSavedLocation = location
    }
}

class FailureLocationDataSource : LocationDatasource {

    override val savedLocation: Flow<Location>
        get() = throw exceptionFixtures

    override fun getFreshLocation(): Flow<Location> =
        throw exceptionFixtures

    override suspend fun saveLastLocation(location: Location) {
        throw exceptionFixtures
    }
}

object LocationRepositoryImplTestFixture {
    val firstSimpleLocation = SimpleLocation(
        lat = 32.637785,
        long = 51.664624
    )
    val secondSimpleLocation = SimpleLocation(
        lat = 31.637785,
        long = 51.664624
    )
    val firstLocationEntity = Location("").apply {
        latitude = 32.637785
        longitude = 51.664624
    }
    val secondLocationEntity = Location("").apply {
        latitude = 31.637785
        longitude = 51.664624
    }
}
