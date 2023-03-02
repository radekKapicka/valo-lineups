package com.example.appka_cvika_1.ui.basic.lazyList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val names = listOf<String>("Petr", "Pavel", "Karel")
val surnames = listOf<String>("Novák", "Krocan", "Havel")


@Composable
fun LazyListScreen(){

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(generateHumans(100)){ human ->
            Box(
                modifier = Modifier.padding(5.dp)
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

data class Human(
    val name: String,
    val surname: String
    )

fun generateHumans(count:Int): List<Human>{

    val list = mutableListOf<Human>()

    repeat(count){ index ->
        list.add(Human(names.random(), surnames.random()))
    }

    return list
}
