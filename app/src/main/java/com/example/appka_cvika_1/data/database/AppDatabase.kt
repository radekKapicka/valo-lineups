package com.example.appka_cvika_1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appka_cvika_1.data.database.DAO.NoteDAO
import com.example.appka_cvika_1.data.database.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = AppDatabase.Version
)
abstract class AppDatabase:RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object{
        const val Version = 1
        const val Name = "UMTEdb"
    }

}