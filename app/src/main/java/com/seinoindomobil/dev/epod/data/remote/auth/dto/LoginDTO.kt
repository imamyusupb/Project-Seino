package com.seinoindomobil.dev.epod.data.remote.auth.dto


data class LoginDTO(
    val result: Result,
    val status: String,
    val status_code: Int,
    val timestamp: String,
    val message :String
)

data class LoginRequest(
    val username:String,
    val password:String
)
