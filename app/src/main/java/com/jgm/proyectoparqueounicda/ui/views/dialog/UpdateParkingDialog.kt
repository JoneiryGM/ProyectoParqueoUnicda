package com.jgm.proyectoparqueounicda.ui.views.dialog


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jgm.proyectoparqueounicda.R
import com.jgm.proyectoparqueounicda.model.businees.Parking
import com.jgm.proyectoparqueounicda.ui.theme.greenColor
import com.jgm.proyectoparqueounicda.ui.theme.primaryColor
import com.jgm.proyectoparqueounicda.viewmodel.HomeViewModel

@Composable
fun UpdateParkingDialog(onDismiss: () -> Unit, homeViewModel: HomeViewModel, parking: Parking) {

    val context = LocalContext.current
    val isAvailableValue = remember { mutableStateOf(false) }

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
                text = "Ajustar disponibilidad parqueo No. ${parking.index}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))


            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                isAvailableValue.value = false
                homeViewModel.updateParking(
                    Parking(
                        parking.id, parking.index, isAvailableValue.value
                    )
                )
                Toast.makeText(
                    context, "Actualización realizada exitosamente!", Toast.LENGTH_SHORT
                ).show()
                onDismiss()
            }, colors = ButtonDefaults.buttonColors(containerColor = primaryColor)) {
                Text(
                    "Ocupado", color = Color.White, fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                isAvailableValue.value = true
                homeViewModel.updateParking(
                    Parking(
                        parking.id, parking.index, isAvailableValue.value
                    )
                )
                Toast.makeText(
                    context, "Actualización realizada exitosamente!", Toast.LENGTH_SHORT
                ).show()
                onDismiss()
            }, colors = ButtonDefaults.buttonColors(containerColor = greenColor)) {
                Text(
                    "Disponible", color = Color.White, fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

        }
    }
}