package com.nstnz.collector.common.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.feature.main.presentation.MainScreen
import com.nstnz.collector.common.test.demos.BottomNavigationDemo
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun App() {
    val navigator = rememberNavigator()
    val navBackStackEntry by navigator.currentEntry.collectAsState(null)
    val currentRoute = navBackStackEntry?.route?.route
    Scaffold(
        topBar = {
            Box(modifier = Modifier.background(MaterialTheme.colors.primary)) {
                TopAppBar(
                    title = { Text(currentRoute ?: "") }
                )
            }
        }
    ) {
        NavHost(navigator = navigator, initialRoute = "route") {
            scene("route") {
                MainScreen()
            }
        }
    }
}