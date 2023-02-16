package com.example.appka_cvika_1.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RadioText(label: String, genderSelection: MutableState<String>){

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(modifier = Modifier.padding(5.dp),
            selected = genderSelection.value == label, onClick = {

            })
        Text(text = label)
    }

}