package com.seinoindomobil.dev.epod.core.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatastore(private val context: Context) {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name ="app_datastore")

    companion object {
        private val TOKEN = stringPreferencesKey(name = "token")
        private val ONBOARD = booleanPreferencesKey("completed")

        @SuppressLint("StaticFieldLeak")
        var INSTANCE: AppDatastore? = null
        fun getInstance(base: Context): AppDatastore? {
            if (INSTANCE == null) {
                synchronized(AppDatastore::class.java) {
                    INSTANCE = AppDatastore(base.applicationContext)
                }
            }

            return INSTANCE
        }
    }

    suspend fun saveOnBoardStatus(isCompleted : Boolean) {
        context.datastore.edit { key ->
            key[ONBOARD] = isCompleted
        }
    }

    suspend fun saveUserToken(token: String) {
        context.datastore.edit { key ->
            key[TOKEN] = token
        }
    }

    val getUserToken: Flow<String> = context.datastore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    val getOnboardStatus: Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[ONBOARD] ?: false
    }


    suspend fun deleteToken() {
        context.datastore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }

}