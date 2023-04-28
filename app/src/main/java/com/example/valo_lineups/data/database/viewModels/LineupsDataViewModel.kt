package com.example.valo_lineups.data.database.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.valo_lineups.data.database.model.Lineups
import com.example.valo_lineups.data.database.model.Maps
import com.example.valo_lineups.data.database.sealed.DataState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LineupsDataViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase(){
        val lineupsList = mutableListOf<Lineups>()
        response.value = DataState.Loading

        FirebaseDatabase.getInstance().getReference("Lineups")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(DataSnap in snapshot.children){
                        val lineupOne = DataSnap.getValue(Lineups::class.java)

                        if (lineupOne != null){
                            lineupsList.add(lineupOne)
                        }
                    }
                    response.value = DataState.SuccessLineups(lineupsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value= DataState.Failure(error.message)
                }
            })

    }
}