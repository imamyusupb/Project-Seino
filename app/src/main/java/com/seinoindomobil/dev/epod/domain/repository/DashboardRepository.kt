package com.seinoindomobil.dev.epod.domain.repository

import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.domain.model.Dashboards
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun getAllDashboardBanner(): Flow<Resource<Dashboards>>
}