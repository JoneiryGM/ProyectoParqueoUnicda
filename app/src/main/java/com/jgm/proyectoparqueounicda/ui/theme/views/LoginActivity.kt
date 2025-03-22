package com.jgm.proyectoparqueounicda.ui.theme.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jgm.proyectoparqueounicda.ui.theme.ProyectoParqueoUnicdaTheme


class LoginActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ProyectoParqueoUnicdaTheme {
            Surface(
                modifier = Modifier.fillMaxSize(), color = Color.White
            ) { LoginScreen() }
        } }
    }

}