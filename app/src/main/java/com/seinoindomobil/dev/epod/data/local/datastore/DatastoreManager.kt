package com.seinoindomobil.dev.epod.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.seinoindomobil.dev.epod.domain.model.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

class DataStoreRepository(private val context: Context) {

    companion object PreferencesKey {
        val ONBOARDING_KEY = booleanPreferencesKey(name = "on_boarding_completed")
        val TOKEN_KEY = stringPreferencesKey(name = "jwt_token")
        val USER_NAME = stringPreferencesKey(name = "user_name")
        val USER_COMPANY = stringPreferencesKey(name = "company")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    fun getOnBoarding(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[ONBOARDING_KEY] ?: false
        }
    }

    suspend fun saveOnBoarding(isCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = isCompleted

        }
    }

    fun getUser(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: ""
            preferences[USER_COMPANY] ?: ""
        }
    }

    suspend fun saveUser(user: List<String>) {
        context.dataStore.edit { preferences ->
           preferences[USER_NAME] = user[0]
            preferences[USER_COMPANY] = user[1]
        }
    }
}