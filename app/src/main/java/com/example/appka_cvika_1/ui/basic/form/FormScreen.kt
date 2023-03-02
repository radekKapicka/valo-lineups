package com.example.appka_cvika_1.ui.basic.form

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appka_cvika_1.R
import com.example.appka_cvika_1.ui.views.RadioText

@Composable
fun FormScreen(){
    val context = LocalContext.current
    val inputName = remember { mutableStateOf("") }
    val inputAge = remember { mutableStateOf("") }
    val genderSelect = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "text")
            },
                navigationIcon = {
                    IconButton(onClick = { (context as Activity).finish() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        }
    ) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.Home, contentDescription = "")
            Image(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "")
            Text(text = context.getString(R.string.form_screen_title), style = MaterialTheme.typography.h4)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputName.value, onValueChange = { text ->
            inputName.value = text },
            label = {
                Text(text = "jméno")
            })
        
        Spacer(modifier = Modifier.height(20.dp))
        
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputAge.value, onValueChange = { text ->
                inputAge.value = text },
            label = {
                Text(text = "věk")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            RadioText("Muž", genderSelect)
            RadioText("Žena", genderSelect)
            RadioText("kokotina", genderSelect)
        }
        
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { (context as Activity).finish() }) {
            Text(text = "Dokončit")
        }

    }
        it
    }
}