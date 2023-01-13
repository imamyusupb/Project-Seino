package com.seinoindomobil.dev.epod.presentation.ui.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.core.util.AppDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val datastore: AppDatastore
) : ViewModel() {

    var isCompleted = mutableStateOf(false)

    fun saveOnBoardingStatus(isCompleted: Boolean) {
        viewModelScope.launch (Dispatchers.IO){
            datastore.saveOnBoardState(isCompleted)
        }
    }
}