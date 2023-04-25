package com.example.valo_lineups.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.data.database.sealed.DataState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DataViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase(){
        val mapsList = mutableListOf<Maps>()
        val agentList = mutableListOf<Maps.Agent>()
        response.value = DataState.Loading

        FirebaseDatabase.getInstance().getReference("Maps")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(DataSnap in snapshot.children){
                        val mapOne = DataSnap.getValue(Maps::class.java)

                        snapshot.child(mapOne!!.uuid).child("agent").children.forEach {
                            val agent = it.getValue(Maps.Agent::class.java)
                            if (agent != null){
                                agentList.add(agent)
                            }
                        }

                        if (mapOne != null){
                            mapsList.add(mapOne)
                        }
                    }
                    response.value = DataState.Success(mapsList, agentList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value=DataState.Failure(error.message)
                }
            })

    }
}
