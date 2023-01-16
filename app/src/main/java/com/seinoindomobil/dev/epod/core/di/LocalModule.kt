package com.seinoindomobil.dev.epod.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.seinoindomobil.dev.epod.data.local.datastore.DataStorePreferenceStorage
import com.seinoindomobil.dev.epod.data.local.datastore.DataStorePreferenceStorage.Companion.PREFS_NAME
import com.seinoindomobil.dev.epod.data.local.datastore.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

   private val Context.datastore by preferencesDataStore(PREFS_NAME)

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): PreferenceStorage = DataStorePreferenceStorage(context.datastore)

}