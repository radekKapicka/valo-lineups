package com.example.appka_cvika_1.ui.basic.form

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class FormActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            FormScreen()
        }
    }
}