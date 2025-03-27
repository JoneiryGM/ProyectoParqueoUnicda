package com.jgm.proyectoparqueounicda.ui.views.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jgm.proyectoparqueounicda.R
import com.jgm.proyectoparqueounicda.model.businees.LoginRequest
import com.jgm.proyectoparqueounicda.model.businees.LoginState
import com.jgm.proyectoparqueounicda.ui.theme.primaryColor
import com.jgm.proyectoparqueounicda.ui.theme.tertiaryColor
import com.jgm.proyectoparqueounicda.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {

    val user by loginViewModel.user.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()
    val userValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(snackbarHost = {

    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor)
                .imePadding()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(32.dp)
            ) {

                Column {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = null,
                        modifier = Modifier
                            .height(180.dp)
                            .padding(bottom = 16.dp)
                    )
                }

                OutlinedTextField(
                    value = userValue.value,
                    onValueChange = { userValue.value = it },
                    label = { Text(stringResource(id = R.string.text_user)) },
                    placeholder = { Text(stringResource(id = R.string.text_user_input)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    singleLine = true,
                    label = { Text(stringResource(id = R.string.text_password)) },
                    placeholder = { Text(stringResource(id = R.string.text_input_password)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image =
                            if (passwordVisible) painterResource(id = R.drawable.baseline_visibility_24)
                            else painterResource(id = R.drawable.baseline_visibility_off_24)
                        val description =
                            if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = image, contentDescription = description)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        //TODO REALIZAR VALIDACIONES CAMPOS VACIOS
                        if (userValue.value.isEmpty() || passwordValue.value.isEmpty()) {
                            Toast.makeText(context, "Debe validar datos", Toast.LENGTH_SHORT).show()
                        } else {
                            loginViewModel.doLogin(
                                LoginRequest(
                                    userValue.value,
                                    passwordValue.value
                                )
                            )
                        }

                        //TODO EVALUAR SI EL USUARIO EXISTE, SI EXISTE PASAR INTENTS CON EL TIPO DE ROL DEL USUARIO A HOME_SCREEN

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = tertiaryColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Ingresar", color = Color.White, fontWeight = FontWeight.Bold
                    )
                }

                when (loginState) {
                    is LoginState.Loading -> "Cargando"
                    is LoginState.Success -> {
                        //TODO EVALUAR SI EL USUARIO EXISTE, SI EXISTE PASAR INTENTS CON EL TIPO DE ROL DEL USUARIO A HOME_SCREEN
                        //val intent = Intent(context, HomeActivity::class.java)
                        // context.startActivity(intent)
                        Log.d("user",user.toString())
                        Toast.makeText(context, "Usuario autenticado", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is LoginState.Error -> {
                        val errorMessage = (loginState as LoginState.Error).message
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }
}

