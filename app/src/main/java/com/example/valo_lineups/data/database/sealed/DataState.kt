package com.example.valo_lineups.data.database.sealed

import com.example.valo_lineups.data.database.model.Lineups
import com.example.valo_lineups.data.database.model.Maps

sealed class DataState {
    class Success(val maps:MutableList<Maps>, val agents:MutableList<Maps.Agent>):DataState()

    class SuccessLineups(val lineups:MutableList<Lineups>):DataState()
    class Failure(val message:String):DataState()
    object Loading:DataState()
    object Empty:DataState()
}