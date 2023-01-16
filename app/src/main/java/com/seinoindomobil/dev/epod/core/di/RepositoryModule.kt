package com.seinoindomobil.dev.epod.core.di

import com.seinoindomobil.dev.epod.data.local.datastore.PreferenceStorage
import com.seinoindomobil.dev.epod.data.remote.LoginApi
import com.seinoindomobil.dev.epod.data.remote.repository.LoginRepositoryImpl
import com.seinoindomobil.dev.epod.data.remote.repository.OnBoardingRepositoryImpl
import com.seinoindomobil.dev.epod.domain.repository.LoginRepository
import com.seinoindomobil.dev.epod.domain.repository.OnBoardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        api: LoginApi,
        preferenceStorage: PreferenceStorage
    ): LoginRepository {
        return LoginRepositoryImpl(api, preferenceStorage)
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(preferenceStorage: PreferenceStorage): OnBoardingRepository {
        return OnBoardingRepositoryImpl(preferenceStorage)
    }
}