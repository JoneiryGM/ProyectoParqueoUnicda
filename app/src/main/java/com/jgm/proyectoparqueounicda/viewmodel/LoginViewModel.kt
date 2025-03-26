package com.jgm.proyectoparqueounicda.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgm.proyectoparqueounicda.model.businees.LoginRequest
import com.jgm.proyectoparqueounicda.model.businees.User
import com.jgm.proyectoparqueounicda.model.repository.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: FirestoreRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun doLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            repository.doSignIn(loginRequest).catch { _user.value = null }
                .collect { foundUser -> _user.value = foundUser }
        }
    }

}