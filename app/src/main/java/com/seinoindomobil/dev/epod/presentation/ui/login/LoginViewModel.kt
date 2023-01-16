package com.seinoindomobil.dev.epod.presentation.ui.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

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
) : ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: LoginState by _loginState

    fun login(username: String, password: String) {
        _loginState.value.isLoading
        viewModelScope.launch {
            loginUseCase.execute(LoginRequest(username, password)).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginState.value = LoginState().copy(
                            isLoading = false,
                            login = result.data,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        _loginState.value = LoginState().copy(
                            isLoading = false,
                            login = null,
                            error = result.message.toString()
                        )
                    }
                    is Resource.Loading -> {
                        _loginState.value = LoginState().copy(
                            isLoading = true,
                            login = null,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.setToken(token)
        }
    }
}
