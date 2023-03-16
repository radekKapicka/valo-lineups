package com.example.appka_cvika_1.ui.database

import com.example.appka_cvika_1.base.BaseViewModel
import com.example.appka_cvika_1.data.database.DAO.NoteDAO
import com.example.appka_cvika_1.data.database.entities.NoteEntity

class DatabaseViewModel(
    private val noteDAO: NoteDAO
):BaseViewModel() {

    val notes =noteDAO.selectAll()

    fun createNote(text: String){
        launch {
            noteDAO.insertOrUpdate(NoteEntity(text = text))
        }
    }

}