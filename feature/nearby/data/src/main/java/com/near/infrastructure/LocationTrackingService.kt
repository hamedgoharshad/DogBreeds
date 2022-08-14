package com.near.infrastructure

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.location.Location
import android.os.Binder
import android.os.IBinder
import android.os.Looper
import androidx.lifecycle.LifecycleService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("MissingPermission")
class LocationTrackingService @Inject constructor() :
    LifecycleService() {

    private var changingConfiguration = false

    @Inject
    lateinit var locationProviderClient: FusedLocationProviderClient

    @Inject
    @ApplicationContext
    lateinit var context: Context

    @Inject
    lateinit var locationRequest: LocationRequest

    fun requestLocationUpdates() =
        locationProviderClient.locationFlow()

    private fun FusedLocationProviderClient.locationFlow() = callbackFlow<Location> {
        val callback = object : LocationCallback(){
            override fun onLocationResult(result: LocationResult) {
                //result ?: return
                result.lastLocation?.let {
                    trySend(it)
                }
            }
        }
        lastLocation.addOnSuccessListener { lastLocation: Location ->
            trySend(lastLocation)
        }
        requestLocationUpdates(
            locationRequest,
            callback, Looper.getMainLooper()
        ).addOnFailureListener { e ->
            close(e)
        }
        awaitClose {
            removeLocationUpdates(callback)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changingConfiguration = true
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        Timber.tag(TAG).e("in onBind()")
        changingConfiguration = false
        return LocalBinder()
    }

    override fun onRebind(intent: Intent) {
        Timber.tag(TAG).e("in onRebind()")
        changingConfiguration = false
        super.onRebind(intent)
    }

    inner class LocalBinder : Binder() {
        val service: LocationTrackingService
            get() = this@LocationTrackingService
    }

    companion object {
        private val TAG = LocationTrackingService::class.java.simpleName
    }
}