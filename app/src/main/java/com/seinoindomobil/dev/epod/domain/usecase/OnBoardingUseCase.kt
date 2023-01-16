package com.seinoindomobil.dev.epod.domain.usecase

import android.content.Context
import com.seinoindomobil.dev.epod.domain.repository.OnBoardingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(private val onBoardingRepository: OnBoardingRepository) {
    suspend fun setOnBoardingStatus(isCompleted :Boolean) = onBoardingRepository.setOnBoardingStatus(isCompleted)
    val getOnBoardingStatus : Flow<Boolean> = onBoardingRepository.getOnBoardingStatus
}