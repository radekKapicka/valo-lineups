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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.valo_lineups.R
import com.example.valo_lineups.data.database.sealed.DataState
import com.example.valo_lineups.data.database.viewModels.DataViewModel
import com.example.valo_lineups.data.database.viewModels.LineupsDataViewModel
import com.example.valo_lineups.ui.async.agents.AgentAPIViewModel
import com.example.valo_lineups.ui.async.base.State
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.valoRed
import com.example.valo_lineups.ui.views.cards.AgentCard
import com.example.valo_lineups.ui.views.cards.LineupCard
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LineupsScreen(
    mapId: String,
    agentId: String,
    dataViewModel: LineupsDataViewModel = viewModel(),
    navHostController: NavHostController,
    APIViewModel: AgentAPIViewModel = getViewModel{
        parametersOf(agentId)
    }
){
    val agentDetailAPI = APIViewModel.agentDetailState.collectAsState()
    val stateAPI = APIViewModel.state.collectAsState()
    val dialogShown = remember{ mutableStateOf(false) }

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

        when (val result = stateAPI.value) {
            State.None, State.Loading -> {
                CircularProgressIndicator()
            }
            is State.Failure -> {
                Button(onClick = { result.repeat() }) {
                    Text(text = "Retry")
                }
            }
            is State.Success -> {
                val detail = agentDetailAPI.value
                if (detail != null) {

                    Text(text = "for agent ${detail.data.displayName}",
                        color = valoRed,
                        fontFamily = Headers,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(10.dp))

                    if (dialogShown.value){
                        AlertDialog(
                            buttons = {
                            },
                            title = {
                                Text(text = "${detail.data.displayName} lore",
                                    color = valoRed,
                                    fontFamily = Headers,
                                    fontSize = 23.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            text = {
                                Text(text = detail.data.description,)
                            },
                            onDismissRequest = {
                                dialogShown.value = false
                            }
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(model = detail.data.displayIcon,
                            contentDescription = "map",
                            modifier = Modifier
                                .width(100.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(onClick = { dialogShown.value = true }) {
                            Text(text = "Agent lore")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                } else {
                    Text(text = "No data available")
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ){

            when(val result = dataViewModel.response.value){
                is DataState.SuccessLineups ->{

                    LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)) {
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