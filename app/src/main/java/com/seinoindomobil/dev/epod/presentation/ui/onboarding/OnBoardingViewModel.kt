package com.seinoindomobil.dev.epod.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCase: OnBoardingUseCase
) : ViewModel() {

    fun saveOnBoardingStatus(isCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
        useCase.setOnBoardingStatus(isCompleted)
        }
    }
}