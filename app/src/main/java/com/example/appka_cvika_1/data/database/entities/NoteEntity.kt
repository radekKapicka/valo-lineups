package com.example.appka_cvika_1.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String = "",
    val priority: Int = 0,
    val solved: Boolean = false
    )