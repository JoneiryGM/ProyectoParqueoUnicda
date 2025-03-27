package com.jgm.proyectoparqueounicda.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgm.proyectoparqueounicda.model.businees.LoginRequest
import com.jgm.proyectoparqueounicda.model.businees.LoginState
import com.jgm.proyectoparqueounicda.model.businees.User
import com.jgm.proyectoparqueounicda.model.repository.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: FirestoreRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginState: StateFlow<LoginState?> = _loginState

    fun doLogin(loginRequest: LoginRequest) {
        Log.d("LoginViewModel", "calling doLogin")
        viewModelScope.launch {
            repository.doSignIn(loginRequest)
                .catch { _loginState.value = LoginState.Error("Error al iniciar sesión") }
                .collect { foundUser ->
                    if (foundUser != null) {
                        _user.value = foundUser
                        _loginState.value = LoginState.Success(foundUser)
                    } else {
                        _loginState.value = LoginState.Error("Credenciales incorectas")
                    }
                }
        }
    }

}