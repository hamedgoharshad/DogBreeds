package com.near.datasource.local

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.location.Location
import android.os.IBinder
import com.near.common.data.persistent.preference.DataStoreConstants
import com.near.common.data.persistent.preference.GenericDataStore
import com.near.datasource.local.interfaces.LocationDatasource
import com.near.mapper.mapToLocation
import com.near.mapper.mapToString
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class LocationDatasourceImpl @Inject constructor(
    private val genericDataStore: GenericDataStore,
    @ApplicationContext context: Context,
) : LocationDatasource {

    private val contextRef = WeakReference(context)
    private var bound = false
    private var serviceConnection: ServiceConnection? = null

    override val savedLocation: Flow<Location> =
        genericDataStore.getValue(DataStoreConstants.LOCATION_DATA_STORE_KEY, String())
            .map { locationString ->
                locationString.mapToLocation()
            }

    @OptIn(FlowPreview::class)
    override fun getFreshLocation(): Flow<Location> =
        callbackFlow {
            val serviceConnection = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Timber.e("connected")
                    bound = true
                    trySend((service as LocationTrackingService.LocalBinder).service.requestLocationUpdates())
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Timber.e("disconnected()")
                    bound = false
                    serviceConnection = null
                }
            }
            contextRef.get()?.bindService(
                Intent(contextRef.get(), LocationTrackingService::class.java),
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
            awaitClose {
                unbindService()
            }
        }.flattenMerge()


    private fun unbindService() {
        Timber.e("in unbindService()")
        if (bound) {
            serviceConnection?.let {
                contextRef.get()?.unbindService(it)
            }
        }
    }

    override suspend fun saveLastLocation(location: Location) =
        genericDataStore.setValue(
            location.mapToString(),
            DataStoreConstants.LOCATION_DATA_STORE_KEY
        )
}