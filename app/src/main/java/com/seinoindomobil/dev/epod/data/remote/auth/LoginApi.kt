package com.seinoindomobil.dev.epod.data.remote.auth

import com.seinoindomobil.dev.epod.data.remote.auth.dto.LoginDTO
import com.seinoindomobil.dev.epod.data.remote.auth.dto.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {

    @POST("authenticate/auth/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginDTO>

    @GET("authenticate/auth/v1/login/refresh")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<LoginDTO>
}