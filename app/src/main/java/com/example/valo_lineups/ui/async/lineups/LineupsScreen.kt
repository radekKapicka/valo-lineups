package com.example.valo_lineups.ui.async.lineups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.valo_lineups.data.database.sealed.DataState
import com.example.valo_lineups.data.database.viewModels.DataViewModel
import com.example.valo_lineups.data.database.viewModels.LineupsDataViewModel
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.valoRed
import com.example.valo_lineups.ui.views.cards.AgentCard
import com.example.valo_lineups.ui.views.cards.LineupCard

@Composable
fun LineupsScreen(
    mapId: String,
    agentId: String,
    dataViewModel: LineupsDataViewModel = viewModel(),
    navHostController: NavHostController
){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "List of reveals",
            color = valoRed,
            fontFamily = Headers,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ){

            when(val result = dataViewModel.response.value){
                is DataState.SuccessLineups ->{

                    LazyColumn() {
                        items(result.lineups) { lineup ->
                            if (lineup.agentUuid.equals(agentId) && lineup.mapUuid.equals(mapId)){

                                LineupCard(lineups = lineup, navHostController = navHostController)

                            }

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
}