package com.example.valo_lineups.ui.views.cards

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.ui.async.lineups.LineupsScreenActivity
import com.example.valo_lineups.ui.navigateDestinationAgentMapScreen
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.Purple200
import com.example.valo_lineups.ui.theme.Teal200
import com.example.valo_lineups.ui.theme.darkGrey
import com.example.valo_lineups.ui.theme.valoRed

@Composable
fun MapCard(
    maps: Maps,
    navHostController: NavHostController,
){
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(5.dp)
        .clickable(onClick = {
            navHostController.navigateDestinationAgentMapScreen(maps.uuid)
        }),
    ) {
        Column (
            modifier = Modifier
                .background(darkGrey)
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text = maps.title,
                color = valoRed,
                fontFamily = Headers,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            AsyncImage(model = maps.image,
                contentDescription = "map",
                modifier = Modifier
                    .height(100.dp)
                    .padding(0.dp,15.dp,0.dp,0.dp)

            )
        }
    }

}

