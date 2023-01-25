package com.seinoindomobil.dev.epod.presentation.ui.dashboard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seinoindomobil.dev.epod.core.util.Resource
import com.seinoindomobil.dev.epod.data.local.datastore.DataStoreRepository
import com.seinoindomobil.dev.epod.domain.usecase.DashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCase: DashboardUseCase,
    private val datastoreRepository:DataStoreRepository
) : ViewModel() {

    private val _dashBoardState: MutableState<DashboardState> = mutableStateOf(DashboardState())
    val dashboardState: DashboardState by _dashBoardState

    var username = mutableStateOf("")
    var company = mutableStateOf("")

    init {
        getAllBannerDashboard()
    }

    fun getAllBannerDashboard() {
        _dashBoardState.value.isLoading

        viewModelScope.launch(Dispatchers.IO) {
            dashboardUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _dashBoardState.value = DashboardState().copy(
                            isLoading = false,
                            dashboard = result.data
                        )
                     datastoreRepository.getUser().collect{
                         username.value = it[0].toString()
                         company.value = it[1].toString()
                     }
                    }
                    is Resource.Error -> {
                        _dashBoardState.value = DashboardState().copy(
                            isLoading = false,
                            dashboard = null)
                    }
                    is Resource.Loading -> {
                        _dashBoardState.value = DashboardState().copy(
                            isLoading = true,
                            dashboard = null)
                    }
                }


            }
        }
    }
}