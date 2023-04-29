package com.example.valo_lineups.data.databaseFirestore.model

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.valo_lineups.ui.auth.updateList
import com.google.firebase.firestore.FirebaseFirestore

object RequestService {

    private val db = FirebaseFirestore.getInstance()
    fun getRequests(request: SnapshotStateList<Requests>) {
        db.collection("Requests").get().addOnSuccessListener {
            request.updateList(it.toObjects(Requests::class.java))
        }.addOnFailureListener {
            request.updateList(listOf())
        }
    }
}