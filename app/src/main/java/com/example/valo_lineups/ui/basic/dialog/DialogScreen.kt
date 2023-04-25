package com.example.valo_lineups.ui.basic.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DialogScreen(){

    val dialogShown = remember{ mutableStateOf(false) }

    if (dialogShown.value){
        AlertDialog(
            buttons = {
                Button(onClick = { dialogShown.value = false }) {
                    Text(text = "OK")
                }
            },
            title = {
                Text(text = "Dialog title")
            },
            text = {
                Text(text = "Dialog text")
            },
            onDismissRequest = {
                dialogShown.value = false
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Button(onClick = { dialogShown.value = true}) {
            Text(text = "Otevři dialog")
        }
    }
}