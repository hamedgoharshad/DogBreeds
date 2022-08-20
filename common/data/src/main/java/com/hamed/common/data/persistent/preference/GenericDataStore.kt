package com.hamed.common.data.persistent.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenericDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    fun <T> getValue(element: Preferences.Key<T>, defaultValue: T) =
        dataStore.data.catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preference ->
            preference[element] ?: defaultValue
        }

    suspend fun <T> setValue(
        value: T,
        element: Preferences.Key<T>
    ) {
        dataStore.edit { pref ->
            pref[element] = value
        }
    }
}
