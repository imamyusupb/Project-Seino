package com.seinoindomobil.dev.epod.domain.usecase

import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.domain.model.Dashboards
import com.seinoindomobil.dev.epod.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {

    suspend fun invoke () :Flow<Resource<Dashboards>>{
        return dashboardRepository.getAllDashboardBanner()
    }
}