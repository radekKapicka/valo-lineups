package com.example.appka_cvika_1.ui.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.koin.androidx.compose.getViewModel
import java.lang.reflect.Modifier

@Composable
fun DatabaseScreen(
    viewModel: DatabaseViewModel = getViewModel()
) {
    val inputText = remember{ mutableStateOf("")}

    Column() {
        LazyColumn{
            //TODO karticky s vypisem
        }

        Row() {
            OutlinedTextField(value = inputText.value, onValueChange = {
                inputText.value = it
            },
            modifier = androidx.compose.ui.Modifier.weight(1F)
            )
            Button(onClick = {
                viewModel.createNote(inputText.value)
                inputText.value = ""
            }) {
                Text("Add")
            }
        }
    }

}