package com.example.valo_lineups.ui.views.cards

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.valo_lineups.R
import com.example.valo_lineups.data.database.model.Lineups
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.ui.basic.bottomNavBar.navigateDestinationAgentMapLineupScreen
import com.example.valo_lineups.ui.basic.bottomNavBar.navigateDestinationLineupDetail
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.darkGrey
import com.example.valo_lineups.ui.theme.valoRed

@Composable
fun LineupCard(
    lineups: Lineups,
    navHostController: NavHostController,
){
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable(onClick = {
                navHostController.navigateDestinationLineupDetail(lineups.uuid)
            }),
    ) {
        Column (
            modifier = Modifier
                .background(darkGrey)
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text = lineups.name,
                color = valoRed,
                fontFamily = Headers,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            AsyncImage(model = lineups.imageFinal,
                contentDescription = "map",
                modifier = Modifier
                    .height(100.dp)
                    .padding(0.dp, 15.dp, 0.dp, 0.dp)

            )
        }
    }

}