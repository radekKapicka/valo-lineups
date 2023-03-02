package com.example.appka_cvika_1.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appka_cvika_1.ui.basic.dialog.DialogActivity
import com.example.appka_cvika_1.ui.basic.form.FormActivity
import com.example.appka_cvika_1.ui.basic.lazyList.LazyListActivity

@Composable
fun HomeScreen(
    parentController: NavHostController
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Hello 2")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Hello 3")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            context.startActivity(Intent(context, FormActivity::class.java))
        }) {
            Icon(Icons.Default.Edit, contentDescription = "")
            Text(text = "Formular")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            context.startActivity(Intent(context, LazyListActivity::class.java))
        }) {
            Icon(Icons.Default.List, contentDescription = "")
            Text(text = "Lazy list")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            context.startActivity(Intent(context, DialogActivity::class.java))
        }) {
            Icon(Icons.Default.Info, contentDescription = "")
            Text(text = "Dialog")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            parentController.navigateRocketLaunchesScreen()
        }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "")
            Text(text = "Async operations")
        }
    }
}