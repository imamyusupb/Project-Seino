package com.seinoindomobil.dev.epod.presentation.ui.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.local.datastore.DataStoreRepository
import com.seinoindomobil.dev.epod.data.remote.auth.dto.LoginRequest
import com.seinoindomobil.dev.epod.domain.usecase.LoginUseCase
import com.seinoindomobil.dev.epod.presentation.ui.login.component.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStoreRepo: DataStoreRepository
) : ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: LoginState by _loginState
    fun login(username: String, password: String) {
        _loginState.value.isLoading
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.execute(LoginRequest(username, password)).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginState.value = LoginState().copy(
                            isLoading = false,
                            login = result.data,
                            error = null
                        )
                        dataStoreRepo.saveToken(result.data?.token ?: "")
                        dataStoreRepo.saveUser(listOf( result.data?.name ?: "" , result.data?.company ?: "" ))
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
}
