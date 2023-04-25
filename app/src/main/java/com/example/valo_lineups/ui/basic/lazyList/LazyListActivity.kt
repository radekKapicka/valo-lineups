package com.example.valo_lineups.ui.basic.lazyList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class LazyListActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            LazyListScreen()
        }
    }
}