package com.jgm.proyectoparqueounicda.ui.views.dialog


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jgm.proyectoparqueounicda.ui.theme.primaryColor
import com.jgm.proyectoparqueounicda.viewmodel.HomeViewModel
import com.jgm.proyectoparqueounicda.R
import com.jgm.proyectoparqueounicda.model.businees.ParkingConfig
import kotlinx.coroutines.launch

@Composable
fun SettingsDialog(onDismiss: () -> Unit, homeViewModel: HomeViewModel) {

    val qtyValue = remember { mutableStateOf("") }
    val currentQty = homeViewModel.currentQtyParking.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        homeViewModel.fetchCurrentParkingConfig()
    }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(8.dp, shape = RoundedCornerShape(12.dp))
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(32.dp)
        ) {

            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 3.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }

            Text(
                text = "Ajustar cantidad de parqueos",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = qtyValue.value,
                onValueChange = {
                    if (it.all { char -> char.isDigit() || char == '.' }) {
                        qtyValue.value = it
                    }
                },
                label = { Text("Cantidad") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    if (qtyValue.value.isEmpty()) {
                        Toast.makeText(
                            context, "Debe ingresar un valor numerico", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        coroutineScope.launch {
                            homeViewModel.updateConfigParking(
                                ParkingConfig(
                                    qty = qtyValue.value.toIntOrNull() ?: 0
                                )
                            )
                        }
                        Toast.makeText(
                            context, "Actualización realizada exitosamente!", Toast.LENGTH_SHORT
                        ).show()
                        onDismiss()
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = primaryColor)) {
                    Text(
                        "Actualizar", color = Color.White, fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
    currentQty.let {
        qtyValue.value = "${it.value?.qty}"
    }

}