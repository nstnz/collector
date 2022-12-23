package com.nstnz.collector.common.basic.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.flow.flowWithLifecycle
import moe.tlaster.precompose.ui.LocalLifecycleOwner

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun <T> StateFlow<T>.collectAsStateLifecycleAware(): State<T> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val lifecycleAwareFlow = remember(this, lifecycleOwner) {
        flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return lifecycleAwareFlow.collectAsState(value)
}