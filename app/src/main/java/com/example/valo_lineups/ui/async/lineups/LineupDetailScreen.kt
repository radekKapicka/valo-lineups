package com.example.valo_lineups.ui.async.lineups

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.valo_lineups.data.database.sealed.DataState
import com.example.valo_lineups.data.database.viewModels.LineupsDataViewModel
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.darkGrey
import com.example.valo_lineups.ui.theme.valoRed
import com.example.valo_lineups.ui.views.cards.LineupCard

@Composable
fun LineupDetailScreen(
    lineupId: String,
    dataViewModel: LineupsDataViewModel = viewModel(),
    navHostController: NavHostController
){


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ){

            when(val result = dataViewModel.response.value){
                is DataState.SuccessLineups ->{

                    for (lineup in result.lineups){
                        if (lineup.uuid.equals(lineupId)){

                            Column (
                                modifier = Modifier
                                    .padding(15.dp)
                                    .verticalScroll(rememberScrollState())
                                    .fillMaxSize().padding(bottom = 50.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ){
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Text(text = lineup.name,
                                        color = valoRed,
                                        fontFamily = Headers,
                                        fontSize = 30.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "UI setup: ",
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp,
                                        color = valoRed,
                                    )
                                    AsyncImage(model = lineup.image,
                                        contentDescription = "lineup image",
                                        modifier = Modifier
                                            .padding(0.dp, 15.dp, 0.dp, 0.dp)
                                            .fillMaxWidth()

                                    )
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Row(
                                    ) {
                                        Text(text = "Site: ",
                                            fontWeight = FontWeight(600),
                                            fontSize = 20.sp,
                                            color = valoRed,
                                        )
                                        Text(text = lineup.site,
                                            fontSize = 20.sp,)
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))

                                        Text(text = "Description: ",
                                            fontWeight = FontWeight(600),
                                            fontSize = 20.sp,
                                            color = valoRed,
                                        )
                                        Text(text = lineup.description,
                                            fontSize = 20.sp,)

                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "Revealed result: ",
                                        fontWeight = FontWeight(600),
                                        fontSize = 20.sp,
                                        color = valoRed,
                                    )
                                    AsyncImage(model = lineup.imageFinal,
                                        contentDescription = "lineup image",
                                        modifier = Modifier
                                            .padding(0.dp, 15.dp, 0.dp, 0.dp)
                                            .fillMaxWidth()

                                    )

                                }

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