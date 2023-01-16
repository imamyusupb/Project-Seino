package com.seinoindomobil.dev.epod.domain.repository

import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.dev.epod.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun postLogin(loginRequest: LoginRequest):Flow<Resource<Login>>
    suspend fun setToken(token:String)
    val getToken :Flow<String>
}