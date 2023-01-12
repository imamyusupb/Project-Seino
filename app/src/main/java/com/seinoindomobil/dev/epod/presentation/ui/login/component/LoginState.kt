package com.seinoindomobil.dev.epod.presentation.ui.login.component

import com.seinoindomobil.dev.epod.domain.model.Login

data class LoginState (
    val isLoading:Boolean = false,
    val login: Login? = null,
    val error :String? = null
)