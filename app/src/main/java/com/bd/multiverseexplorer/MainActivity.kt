package com.bd.multiverseexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bd.multiverseexplorer.navigation.AppNavigation
import com.bd.multiverseexplorer.presentation.theme.MultiverseExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiverseExplorerTheme {
                AppNavigation()
            }
        }
    }
}