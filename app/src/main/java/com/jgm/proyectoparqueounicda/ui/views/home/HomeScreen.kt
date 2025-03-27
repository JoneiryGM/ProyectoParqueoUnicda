package com.jgm.proyectoparqueounicda.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = "Soy el menu principal}",
            color = Color.Black,
            modifier = Modifier.padding(top = 26.dp)
        )
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(columns),
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.weight(1f)
//        ) {
//            items(tables) { table ->
//                TableItem(table)
//            }
//                    }
//            }
}
}