package com.nstnz.collector.common.basic.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import moe.tlaster.precompose.lifecycle.Lifecycle
import moe.tlaster.precompose.lifecycle.LifecycleObserver
import moe.tlaster.precompose.lifecycle.LifecycleOwner
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.ui.LocalLifecycleOwner

internal fun Navigator.navigate(routes: Routes, options: NavOptions? = null, vararg args: Any?) {
    this.navigate(
        routes.name + "?$Arg1=${args.getOrNull(0) ?: ""}" +
                "&$Arg2=${args.getOrNull(1) ?: ""}" +
                "&$Arg3=${args.getOrNull(2) ?: ""}",
        options
    )
}

@Composable
internal fun OnLifecycleEvent(onEvent: (event: Lifecycle.State) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = object : LifecycleObserver {
            override fun onStateChanged(state: Lifecycle.State) {
                eventHandler.value(state)
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}