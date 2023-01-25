package com.seinoindomobil.dev.epod.data.remote.dashboard.dto

data class DashboardDTO(
    val result: Result,
    val status: String,
    val status_code: Int,
    val timestamp: String
)