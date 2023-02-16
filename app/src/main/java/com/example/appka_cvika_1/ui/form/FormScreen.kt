package com.example.appka_cvika_1.ui.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.appka_cvika_1.R

@Composable
fun FormScreen(){

    val inputName = remember { mutableStateOf("") }

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.Home, contentDescription = "")
            Image(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "")
            Text(text = "Vyplňte form", style = MaterialTheme.typography.h4)
        }
        OutlinedTextField(
            value = inputName.value, onValueChange = { text ->
            inputName.value = text },
            label = {
                Text(text = "jméno")
            })
    }
}