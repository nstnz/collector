package com.nstnz.collector.common.basic.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import com.nstnz.collector.common.basic.router.DIModelsTags.getDiTag
import com.nstnz.collector.common.basic.router.DIModelsTags.resetDiTag
import moe.tlaster.precompose.lifecycle.Lifecycle
import moe.tlaster.precompose.lifecycle.LifecycleObserver
import moe.tlaster.precompose.lifecycle.LifecycleOwner
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.ui.LocalLifecycleOwner
import kotlin.random.Random
import kotlin.reflect.KClass

internal fun Navigator.navigate(routes: Routes, options: NavOptions? = null, vararg args: Any?) {
    this.navigate(
        routes.name + "?$Arg1=${args.getOrNull(0) ?: ""}" +
                "&$Arg2=${args.getOrNull(1) ?: ""}" +
                "&$Arg3=${args.getOrNull(2) ?: ""}",
        options
    )
}

//ugly workaround as Kodein DI is not good with scopes
internal object DIModelsTags {
    private val rand = Random(Long.MAX_VALUE)
    private val map = mutableMapOf<KClass<*>, Long>()

    fun getDiTag(klass: KClass<*>) = map[klass] ?: kotlin.run {
        val tag = rand.nextLong()
        map[klass] = tag
        tag
    }

    fun resetDiTag(klass: KClass<*>) = map.remove(klass)
}

@Composable
internal fun OnLifecycleEvent(vmType: KClass<*>, onEvent: (event: Lifecycle.State) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        lateinit var observer: LifecycleObserver
        observer = object : LifecycleObserver {
            override fun onStateChanged(state: Lifecycle.State) {
                eventHandler.value(state)

                if (state == Lifecycle.State.Destroyed) {
                    lifecycle.removeObserver(observer)
                    resetDiTag(vmType)
                }
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
        }
    }
}