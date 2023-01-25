package com.seinoindomobil.dev.epod.data.remote.auth.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.mapper.toLoginDomain
import com.seinoindomobil.dev.epod.data.remote.auth.LoginApi
import com.seinoindomobil.dev.epod.data.remote.auth.dto.LoginRequest
import com.seinoindomobil.dev.epod.domain.model.Login
import com.seinoindomobil.dev.epod.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
) : LoginRepository{
    override suspend fun postLogin(loginRequest: LoginRequest): Flow<Resource<Login>> = flow {
        try {
            emit(Resource.Loading<Login>())
            val result = loginApi.login(loginRequest)

            if (result.isSuccessful) {
                emit(Resource.Success(result.body()!!.toLoginDomain()))
            } else {
                val type = object : TypeToken<Login>() {}.type
                val error: Login = Gson().fromJson(result.errorBody()!!.charStream(), type)
                error.message = result.message()
                emit(Resource.Error(error.toString()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<Login>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Login>("Couldn't reach server. Check your internet connection."))
        }
    }
}