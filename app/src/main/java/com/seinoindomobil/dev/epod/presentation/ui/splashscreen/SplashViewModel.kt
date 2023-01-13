package com.seinoindomobil.dev.epod.presentation.ui.splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.core.util.AppDatastore
import com.seinoindomobil.dev.epod.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val datastore: AppDatastore
) : ViewModel() {

    private val _onBoardingCompleted = mutableStateOf(false)
    val onBoardingCompleted get() = _onBoardingCompleted

    private val _tokenIsEmpty = mutableStateOf("")
    val tokenIstEmpty get() = _tokenIsEmpty

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.HomeScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            datastore.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.LoginScreen.route
                } else {
                    _startDestination.value = Screen.OnBoardingScreen.route
                }
                _onBoardingCompleted.value = true
            }

           datastore.readUserTokenState().collect { token ->
               if (token != ""){
                   _startDestination.value = Screen.HomeScreen.route
               }else{
                   _startDestination.value = Screen.OnBoardingScreen.route
               }
               _tokenIsEmpty.value = token
           }
        }
    }
}
