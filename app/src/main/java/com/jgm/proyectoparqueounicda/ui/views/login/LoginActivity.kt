package com.jgm.proyectoparqueounicda.ui.views.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jgm.proyectoparqueounicda.ui.theme.ThemeApp
import com.jgm.proyectoparqueounicda.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject

class LoginActivity : ComponentActivity() {

    private val loginViewModel:LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeApp {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {
                    LoginScreen(loginViewModel)
                }
            }
        }
    }

}