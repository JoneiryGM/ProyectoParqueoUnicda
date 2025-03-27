package com.jgm.proyectoparqueounicda.model.businees

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}