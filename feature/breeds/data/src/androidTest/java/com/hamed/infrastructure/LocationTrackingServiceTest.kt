package com.hamed.infrastructure

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import kotlinx.coroutines.flow.first
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class.java)
@MediumTest
class LocationTrackingServiceTest {
    @get:Rule
    val serviceRule = ServiceTestRule()

    @Test fun `request location updates get not null value`() {
        val binder = serviceRule.bindService(
            Intent(ApplicationProvider.getApplicationContext(),
                LocationTrackingService::class.java))
        val service = (binder as LocationTrackingService.LocalBinder).service
        assert(service.requestLocationUpdates().first() != null)
    }
}