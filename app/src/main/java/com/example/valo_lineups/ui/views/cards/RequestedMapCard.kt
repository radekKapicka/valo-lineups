package com.example.valo_lineups.ui.views.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.ui.basic.bottomNavBar.navigateDestinationAgentMapLineupScreen
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.darkGrey
import com.example.valo_lineups.ui.theme.valoRed
import com.google.firebase.auth.FirebaseUser


@Composable
fun RequestedMapCard(
    agent: String,
    map: String,
    destination: String,
    user: String,
    navHostController: NavHostController,
){
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(5.dp),
    ) {
        Column (
            modifier = Modifier
                .background(darkGrey)
                .padding(15.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = destination,
                color = valoRed,
                fontFamily = Headers,
                fontSize = 23.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row() {
                        Text(text = "Map: ",
                            color = Color.White,
                            fontWeight = FontWeight(600))
                        Text(text = map, color = Color.White)
                    }
                }
                Column(Modifier.weight(1f)) {
                    Row() {
                        Text(text = "Agent: ", color = Color.White, fontWeight = FontWeight(600))
                        Text(text = agent, color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row() {
                Text(text = "Submited by: ", color = Color.White, fontWeight = FontWeight(600))
                Text(text = user, color = Color.White)
            }

            
        }
    }

}