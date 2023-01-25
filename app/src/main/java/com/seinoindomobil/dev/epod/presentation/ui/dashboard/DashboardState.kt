package com.seinoindomobil.dev.epod.presentation.ui.dashboard

import com.seinoindomobil.dev.epod.domain.model.Dashboards

data class DashboardState (
    val isLoading : Boolean = true,
    val dashboard :Dashboards? = null,
    )