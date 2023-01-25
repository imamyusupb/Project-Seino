package com.seinoindomobil.dev.epod.data.remote.dashboard.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.mapper.toDashboardDomain
import com.seinoindomobil.dev.epod.data.remote.dashboard.DashboardApi
import com.seinoindomobil.dev.epod.domain.model.Dashboards
import com.seinoindomobil.dev.epod.domain.model.Login
import com.seinoindomobil.dev.epod.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dashboardApi :DashboardApi
) :DashboardRepository{
    override suspend fun getAllDashboardBanner(): Flow<Resource<Dashboards>> {
       return flow {
            try {
                emit(Resource.Loading<Dashboards>())
                val result = dashboardApi.getAllBannerDashboard()

                if (result.isSuccessful){
                    emit(Resource.Success(result.body()!!.toDashboardDomain()))
                }else {
                    val type = object : TypeToken<Dashboards>() {}.type
                    val error: Login = Gson().fromJson(result.errorBody()!!.charStream(), type)
                    error.message = result.message()
                    emit(Resource.Error(error.toString()))
                }
            }catch (e: HttpException) {
                emit(Resource.Error<Dashboards>(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error<Dashboards>("Couldn't reach server. Check your internet connection."))
            }
       }
    }
}