package com.jgm.proyectoparqueounicda.ui.views.home


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jgm.proyectoparqueounicda.R
import com.jgm.proyectoparqueounicda.model.businees.Parking
import com.jgm.proyectoparqueounicda.ui.theme.greenColor
import com.jgm.proyectoparqueounicda.ui.theme.primaryColor
import com.jgm.proyectoparqueounicda.viewmodel.HomeViewModel

@Composable
fun HomeScreen(rol: String?, homeViewModel: HomeViewModel) {

    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3
    val parking = homeViewModel.parking.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchAllParking()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            parking.value.sortedBy { it.index }.stream().forEach { parking ->
                item(parking.index) {
                    ParkingItem(rol = rol, parking)
                }
            }
        }
    }
}


@Composable
fun ParkingItem(rol: String?, parking: Parking) {
    LocalContext.current
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clickable {
                if (rol.equals("admin")) {
                    //TODO CLICK SHOW DIALOG UPDATE PARKING SLOT
                }
            }
            .background(
                color = if (parking.isAvailable) greenColor else primaryColor,
                shape = RoundedCornerShape(12.dp)
            ),
    ) {
        IconButton(
            onClick = {
                if (rol.equals("admin")) {
                    //TODO CLICK SHOW DIALOG UPDATE PARKING SLOT
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_local_parking_72),
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = parking.index.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp
        )
    }
}