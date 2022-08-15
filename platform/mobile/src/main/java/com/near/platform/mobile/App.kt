package com.near.platform.mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.near.platform.mobile.navigation.BreedsNavHost


@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier,
            bottomBar = {
            }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
            ) {
                BreedsNavHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(padding).background(Color.Red)
                )
            }
        }
    }
}