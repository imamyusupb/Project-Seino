package com.seinoindomobil.dev.epod.data.remote.dashboard

import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.remote.dashboard.dto.DashboardDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {

    @GET("epod/dashboard/v1")
    suspend fun getAllBannerDashboard() : Response<DashboardDTO>
}