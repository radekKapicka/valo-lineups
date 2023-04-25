package com.example.valo_lineups

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.valo_lineups.data.DataViewModel
import com.example.valo_lineups.ui.AppContainer
import com.example.valo_lineups.ui.HomeScreen
import com.example.valo_lineups.ui.basic.bottomNavBar.BottomNavBarScreen
import com.example.valo_lineups.ui.theme.Appkacvika1Theme

class MainActivity : ComponentActivity() {
    private val viewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Appkacvika1Theme {
                //HomeScreen(viewModel)
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    AppContainer(
                        controller = rememberNavController(),
                    )
                }
            }
        }
    }
}