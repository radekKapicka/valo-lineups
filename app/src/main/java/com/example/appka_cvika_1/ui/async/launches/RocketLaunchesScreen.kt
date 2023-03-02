package com.example.appka_cvika_1.ui.async.launches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appka_cvika_1.ui.basic.lazyList.generateHumans

@Composable
fun RocketLaunchesScreen(
    onNavigateDetail:(rocketId:String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(generateHumans(100)){ human -> //TODO data z API místo humans
            Box(
                modifier = Modifier.padding(5.dp).clickable {
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
                        Text(text = human.name)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = human.surname)
                    }
                }
            }
        }
    }

}