package com.seinoindomobil.dev.epod.core.di

import com.seinoindomobil.dev.epod.data.remote.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)


}