package com.example.valo_lineups.ui.async.lineups

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.valo_lineups.ui.basic.form.FormScreen

class LineupsScreenActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            LineupsScreen()
        }
    }
}