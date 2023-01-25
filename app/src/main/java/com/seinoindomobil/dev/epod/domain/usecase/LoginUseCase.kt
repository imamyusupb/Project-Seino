package com.seinoindomobil.dev.epod.domain.usecase

import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.remote.auth.dto.LoginRequest
import com.seinoindomobil.dev.epod.domain.model.Login
import com.seinoindomobil.dev.epod.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend fun execute(loginRequest: LoginRequest): Flow<Resource<Login>> {
        return repository.postLogin(loginRequest)
    }
}