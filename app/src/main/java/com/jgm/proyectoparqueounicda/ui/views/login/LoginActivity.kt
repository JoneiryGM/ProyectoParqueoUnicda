package com.jgm.proyectoparqueounicda.ui.views.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jgm.proyectoparqueounicda.ui.theme.ThemeApp

class LoginActivity : ComponentActivity() {

    //TODO INSTANCIAR EL VIEW MODEL AQUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeApp {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {
                    //TODO PASR EL VIEW_MODEL COMO PRAMETRO A LA FUNCION COMPOSABLE
                    LoginScreen()
                }
            }
        }
    }

}