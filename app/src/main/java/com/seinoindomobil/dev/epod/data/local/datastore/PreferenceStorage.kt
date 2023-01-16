package com.seinoindomobil.dev.epod.data.local.datastore

import com.seinoindomobil.dev.epod.core.di.RepositoryModule
import dagger.Component
import kotlinx.coroutines.flow.Flow


interface PreferenceStorage {
    suspend fun setOnboardingState(isCompleted :Boolean)
    val getOnboardState :Flow<Boolean>

    suspend fun setUserToken(token:String)
    val getUserToken :Flow<String>
    suspend fun deleteToken()

}