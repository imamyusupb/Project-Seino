package com.seinoindomobil.dev.epod.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.map
import java.io.IOException


class AppDatastore(context: Context) {

    companion object {
        val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "app_datastore")
        private val TOKEN = stringPreferencesKey(name = "token")
        private val ONBOARD = booleanPreferencesKey("onboard")
    }

    private val dataStore = context.datastore

    suspend fun saveOnBoardState(isCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARD] = isCompleted
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[ONBOARD] ?: false
                onBoardingState
            }
    }

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    fun readUserTokenState(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val tokenState = preferences[TOKEN] ?: ""
                tokenState
            }
    }

    suspend fun deleteToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }
}