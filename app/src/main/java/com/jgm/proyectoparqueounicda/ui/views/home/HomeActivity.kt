package com.jgm.proyectoparqueounicda.ui.views.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.jgm.proyectoparqueounicda.R
import com.jgm.proyectoparqueounicda.ui.theme.ThemeApp
import com.jgm.proyectoparqueounicda.ui.theme.primaryColor
import com.jgm.proyectoparqueounicda.ui.views.dialog.SettingsDialog
import com.jgm.proyectoparqueounicda.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3Api::class)
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rol = intent.getStringExtra("rol")
            var showSettingsDialog by remember { mutableStateOf(false) }
            ThemeApp {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var topBarTitle by remember { mutableStateOf("INICIO") }
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = primaryColor,
                                titleContentColor = Color.White,
                            ), title = { Text(topBarTitle) }, actions = {
                                //TODO PONER BOTON DE CERRAR SESION AQUI DEBAJO DE ESTE
                                if (rol.equals("admin")) {
                                    IconButton(onClick = {
                                        showSettingsDialog = true
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_settings_24),
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                }
                            })
                        },
                        bottomBar = {},
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            HomeScreen(rol, homeViewModel)
                        }
                    }
                }
            }
            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss = { showSettingsDialog = false }, homeViewModel = homeViewModel
                )
            }
        }
    }
}