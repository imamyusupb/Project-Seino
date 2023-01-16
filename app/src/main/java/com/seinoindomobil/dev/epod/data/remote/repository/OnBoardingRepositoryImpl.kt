package com.seinoindomobil.dev.epod.data.remote.repository

import com.seinoindomobil.dev.epod.data.local.datastore.PreferenceStorage
import com.seinoindomobil.dev.epod.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(private val dataStore: PreferenceStorage) : OnBoardingRepository{
    override suspend fun setOnBoardingStatus(isCompleted: Boolean) {
        dataStore.setOnboardingState(isCompleted)
    }

    override val getOnBoardingStatus: Flow<Boolean>
        get() = dataStore.getOnboardState
}