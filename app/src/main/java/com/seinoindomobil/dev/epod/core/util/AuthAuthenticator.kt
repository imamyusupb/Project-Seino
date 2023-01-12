package com.seinoindomobil.dev.epod.core.util

import com.seinoindomobil.dev.epod.data.remote.LoginApi
import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginDTO
import com.seinoindomobil.dev.epod.domain.model.Login
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor (
    private val tokenManager: AppDatastore,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getUserToken.first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
                tokenManager.deleteToken()
            }

            newToken.body()?.let {
                tokenManager.saveUserToken(it.result.signature?.access_token.toString())
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.result.signature?.access_token}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginDTO> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.203.64:5555/authenticate/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(LoginApi::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }
}