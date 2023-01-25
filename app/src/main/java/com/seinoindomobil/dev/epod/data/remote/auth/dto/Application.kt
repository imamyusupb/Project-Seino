package com.seinoindomobil.dev.epod.data.remote.auth.dto

data class Application(
    val feature: List<Feature>,
    val name: String,
    val version: String
)