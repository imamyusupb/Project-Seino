package com.seinoindomobil.dev.epod.core.di

import android.content.Context
import com.seinoindomobil.dev.epod.core.util.AppDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): AppDatastore = AppDatastore(context)

}