package com.example.appka_cvika_1.ui.basic.lazyList

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