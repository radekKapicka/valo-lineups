package com.example.valo_lineups.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import androidx.compose.material.Card
import androidx.compose.ui.graphics.Color
import com.example.valo_lineups.data.repository.ValoRepository
import com.example.valo_lineups.ui.async.launches.MapsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.valo_lineups.data.DataViewModel
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.data.database.sealed.DataState
import com.example.valo_lineups.ui.async.lineups.LineupsScreen
import com.example.valo_lineups.ui.async.lineups.LineupsScreenActivity
import com.example.valo_lineups.ui.views.cards.MapCard

@Composable
fun HomeScreen(
    dataViewModel: DataViewModel,
    navHostController: NavHostController
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
                        MapCard(mapka,navHostController)
                    }
                }
                LazyColumn() {
                    items(result.agents){ agent ->
                        Text(text = agent.name)
                    }
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