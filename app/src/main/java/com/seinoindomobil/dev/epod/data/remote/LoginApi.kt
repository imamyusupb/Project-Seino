package com.seinoindomobil.dev.epod.data.remote

import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginDTO
import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.dev.epod.domain.model.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginDTO>

    @GET("auth/v1/login/refresh")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<LoginDTO>
}