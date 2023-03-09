package com.example.appka_cvika_1.ui.async.launches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appka_cvika_1.base.State
import com.example.appka_cvika_1.data.model.response.RocketLaunchResponse
import com.example.appka_cvika_1.ui.basic.lazyList.generateHumans
import org.koin.androidx.compose.getViewModel
import androidx.compose.runtime.State as NewState

@Composable
fun RocketLaunchesScreen(
    onNavigateDetail:(rocketId:String) -> Unit,
    viewModel: RocketLaunchesViewModel = getViewModel()
) {

    val launches = viewModel.successRocketLaunches.collectAsState()
    val state = viewModel.state.collectAsState()
    
    when(val result = state.value){
        State.None, State.Loading -> {
            CircularProgressIndicator()
        }
        is State.Failure -> {
            Text(text = "Chyba! - ${result.throwable.localizedMessage}")
        }
        is State.Success -> {
            RocketLaunchesView(launches, onNavigateDetail)
        }
    }

}
@Composable
fun RocketLaunchesView(
    rocketLaunches : NewState<List<RocketLaunchResponse>>,
    onNavigateDetail:(rocketId:String) -> Unit,
){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(rocketLaunches.value){ rocketLaunch ->
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        //akce po kliku
                        onNavigateDetail.invoke("") //TODO dodat rocketID
                    }
            ){
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = rocketLaunch.missionName)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Flight num: ${rocketLaunch.flightNumber}")
                    }
                }
            }
        }
    }
}