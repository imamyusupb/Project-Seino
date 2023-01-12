package com.seinoindomobil.dev.epod.presentation.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.core.util.AppDatastore
import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginRequest
import com.seinoindomobil.dev.epod.domain.usecase.LoginUseCase
import com.seinoindomobil.dev.epod.presentation.ui.login.component.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: AppDatastore
) : ViewModel() {

    var state by mutableStateOf(LoginState())
    var token by mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getUserToken.collect {
                withContext(Dispatchers.Main) {
                    token = it
                }
            }
        }
    }

    fun login(username: String, password: String) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            loginUseCase.execute(LoginRequest(username, password)).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            login = result.data, error = null, isLoading = false
                        )
                        saveToken(state.login?.token.toString())
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false, login = null, error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.saveUserToken(token)
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.deleteToken()
        }
    }
}
