package com.seinoindomobil.dev.epod.presentation.ui.splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.data.local.datastore.DataStoreRepository
import com.seinoindomobil.dev.epod.domain.usecase.LoginUseCase
import com.seinoindomobil.dev.epod.presentation.navigation.Screen
import com.seinoindomobil.dev.epod.presentation.ui.login.component.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _splashState: MutableState<SplashState> = mutableStateOf(SplashState())
    val startDestination: State<SplashState> = _splashState

    init {
        getToken()
        getOnBoarding()
    }

    fun getToken() {
        viewModelScope.launch {
            dataStoreRepository.getToken().collect {
                if (it != null) {
                    _splashState.value = SplashState().copy(
                        token = it,
                        destination = Screen.DashboardScreen.route
                    )
                }else{
                    _splashState.value = SplashState().copy(
                        token = null,
                        destination = Screen.LoginScreen.route
                    )
                }
            }
        }
    }

    fun getOnBoarding() {
        viewModelScope.launch {
            dataStoreRepository.getOnBoarding().collect { isCompleted ->
                if (!isCompleted) {
                    _splashState.value = SplashState().copy(
                        destination = Screen.OnBoardingScreen.route
                    )
                } else {
                    _splashState.value = SplashState().copy(
                        destination = Screen.LoginScreen.route
                    )
                }
            }
        }
    }
}




