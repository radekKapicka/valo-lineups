package com.example.appka_cvika_1.data.database.DAO

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appka_cvika_1.data.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun selectAll(): Flow<List<NoteEntity>>

}