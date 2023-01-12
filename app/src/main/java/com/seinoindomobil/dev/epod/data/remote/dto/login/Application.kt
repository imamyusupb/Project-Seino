package com.seinoindomobil.dev.epod.data.remote.dto.login

import com.seinoindomobil.dev.epod.data.remote.dto.login.Feature

data class Application(
    val feature: List<Feature>,
    val name: String,
    val version: String
)