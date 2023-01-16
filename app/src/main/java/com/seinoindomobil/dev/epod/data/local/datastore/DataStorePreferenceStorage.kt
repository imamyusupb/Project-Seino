package com.seinoindomobil.dev.epod.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seinoindomobil.dev.epod.data.local.datastore.DataStorePreferenceStorage.PreferenceKeys.USER_TOKEN
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {


    object PreferenceKeys {
        val IS_ONBOARD_COMPLETED = booleanPreferencesKey("IS_ONBOARD_COMPLETED")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    companion object {
        const val PREFS_NAME = "my_datastore"
    }

    override suspend fun setOnboardingState(isCompleted: Boolean) {
        dataStore.setValue {
            it[PreferenceKeys.IS_ONBOARD_COMPLETED] = isCompleted
        }
    }

    override val getOnboardState: Flow<Boolean>
        get() = dataStore.getValue {
            it[PreferenceKeys.IS_ONBOARD_COMPLETED] ?: false
        }

    override suspend fun setUserToken(token: String) {
       dataStore.setValue {
           it[PreferenceKeys.USER_TOKEN] = token
       }
    }

    override val getUserToken: Flow<String>
        get() = dataStore.getValue {
            it[PreferenceKeys.USER_TOKEN] ?: ""
        }

    override suspend fun deleteToken() {
        dataStore.setValue {
            it.remove(USER_TOKEN)
        }
    }
}