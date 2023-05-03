package com.example.valo_lineups.ui.basic.form

import android.app.Activity
import android.util.Size
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.valo_lineups.R

import com.example.valo_lineups.data.databaseFirestore.model.Requests
import com.example.valo_lineups.ui.basic.bottomNavBar.navigateRequestedByusers
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun FormScreen(
    navHostController: NavHostController,
    email: String,
){
    val db = Firebase.firestore
    val context = LocalContext.current
    val inputMap = remember { mutableStateOf(TextFieldValue()) }
    val inputAgent = remember { mutableStateOf(TextFieldValue()) }
    val destination = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
        }


        val mapSelect = dropDownMenuMap()
        Spacer(modifier = Modifier.height(10.dp))
        val agentSelect = dropDownMenuAgent()

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = destination.value, onValueChange = { text ->
                destination.value = text },
            label = {
                Text(text = "Destination")
            },
        )
        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = {

            val request = Requests(
                map = mapSelect,
                agent = agentSelect,
                destination = destination.value.text,
                user = email
            )

            db.collection("Requests")
                .add(request)
                .addOnCompleteListener{
                    if(it.isSuccessful) {
                        navHostController.navigateRequestedByusers()
                        Toast.makeText(context,"Request added",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,"Error: ${it.exception?.message}",Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(context,"Error: ${it.message}",Toast.LENGTH_SHORT).show()
                }


        }) {
            Text(text = "Submit")
        }

    }
}

@Composable
fun dropDownMenuMap():String {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Breeze", "Bind", "Split", "Haven", "Ascent", "Icebox", "Fracture")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Select map")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
    return selectedText

}

@Composable
fun dropDownMenuAgent():String {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Astra", "Breach", "Brimstone", "Chamber",
        "Cypher", "Jett", "Kay/O", "Killjoy", "Neon", "Omen", "Phoenix",
        "Raze", "Reyna", "Sage", "Skye", "Sova", "Viper", "Yoru")

    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Select Agent")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
    return selectedText

}