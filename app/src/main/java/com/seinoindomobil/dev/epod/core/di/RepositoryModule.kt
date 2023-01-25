package com.seinoindomobil.dev.epod.core.di

import com.seinoindomobil.dev.epod.data.remote.auth.LoginApi
import com.seinoindomobil.dev.epod.data.remote.auth.repository.LoginRepositoryImpl
import com.seinoindomobil.dev.epod.data.remote.dashboard.DashboardApi
import com.seinoindomobil.dev.epod.data.remote.dashboard.repository.DashboardRepositoryImpl
import com.seinoindomobil.dev.epod.domain.repository.DashboardRepository
import com.seinoindomobil.dev.epod.domain.repository.LoginRepository
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
    fun provideLoginRepository(api: LoginApi, ): LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDashboardRepository(api :DashboardApi) :DashboardRepository {
        return DashboardRepositoryImpl(api)
    }
}