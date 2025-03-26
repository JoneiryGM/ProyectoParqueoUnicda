package com.jgm.proyectoparqueounicda.di


import com.jgm.proyectoparqueounicda.model.repository.FirestoreRepository
import com.jgm.proyectoparqueounicda.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { FirestoreRepository() }
    viewModelOf(::LoginViewModel)
}