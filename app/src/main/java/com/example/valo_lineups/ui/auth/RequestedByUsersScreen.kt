package com.example.valo_lineups.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.valo_lineups.data.databaseFirestore.model.RequestService
import com.example.valo_lineups.data.databaseFirestore.model.Requests
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.valoRed
import com.example.valo_lineups.ui.views.cards.RequestedMapCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var mAuth: FirebaseAuth

@Composable
fun RequestedByUsersScreen(
    navHostController: NavHostController
){
    val db = Firebase.firestore
    mAuth = FirebaseAuth.getInstance()
    val requests = remember { mutableStateListOf(Requests()) }
    RequestService.getRequests(requests)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Reveals already submited by users",
            color = valoRed,
            fontFamily = Headers,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)
        ) {
            items(requests.size) { index ->
                RequestedMapCard(
                    agent = requests[index].agent,
                    map = requests[index].map,
                    destination = requests[index].destination,
                    user = requests[index].user,
                    navHostController = navHostController
                )
            }
        }


    }
}

fun <T> SnapshotStateList<T>.updateList(newList: List<T>){
    clear()
    addAll(newList)
}