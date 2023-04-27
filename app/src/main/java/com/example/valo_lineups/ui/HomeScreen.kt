package com.example.valo_lineups.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import com.example.valo_lineups.data.DataViewModel
import com.example.valo_lineups.data.database.sealed.DataState
import com.example.valo_lineups.ui.basic.bottomNavBar.navigateLineups
import com.example.valo_lineups.ui.views.cards.MapCard

@Composable
fun HomeScreen(
    dataViewModel: DataViewModel,
    parentController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Choose map")
        Spacer(modifier = Modifier.height(20.dp))

        when(val result = dataViewModel.response.value){
            is DataState.Success ->{
                LazyColumn() {
                    items(result.maps){ mapka ->
                        MapCard(mapka,parentController)
                    }
                }
                LazyColumn() {
                    items(result.agents){ agent ->
                        Text(text = agent.name)
                    }
                }
                Button(onClick = { parentController.navigateLineups() }) {
                    
                }
            }
            is DataState.Loading ->{
                Box(modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center,
                ){
                    CircularProgressIndicator()
                }
            }
            is DataState.Failure ->{
                Box(modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ){
                    Text(text = result.message)
                }
            }else ->{
            Box(modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ){
                Text(text = "error loading")
            }
        }
        }
    }


}