package com.hamed.common.data.persistent.preference

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConstants {
    const val DATA_STORE_NAME = "location_data_store"
    val LOCATION_DATA_STORE_KEY = stringPreferencesKey("LOCATION")
}