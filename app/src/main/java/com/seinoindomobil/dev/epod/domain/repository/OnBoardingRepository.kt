package com.seinoindomobil.dev.epod.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {

    suspend fun setOnBoardingStatus(isCompleted :Boolean)
    val getOnBoardingStatus :Flow<Boolean>
}