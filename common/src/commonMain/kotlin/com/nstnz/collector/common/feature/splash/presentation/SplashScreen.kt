package com.nstnz.collector.common.feature.splash.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.accentColor
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    viewState: SplashScreenState,
    onReady: () -> Unit = {}
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
        delay(1000)
        onReady()
    }

    GradientScaffold {
        Box(Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = visible,
                modifier = Modifier.align(Alignment.Center),
                enter = fadeIn()
            ) {
                Column(
                    Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.Rounded.AccountBalanceWallet,
                        null,
                        modifier = Modifier.size(AppTheme.indents.x9)
                            .align(Alignment.CenterHorizontally),
                        tint = AppTheme.colors.accentColor()
                    )
                    SpacerComponent { x4 }
                    Text(
                        text = strings.Core_AppName,
                        color = AppTheme.colors.primaryBackgroundText(),
                        style = AppTheme.typography.headingMegaLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    SpacerComponent { x0_5 }
                    Text(
                        text = strings.Core_AppSlogan,
                        color = AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                }
            }
        }
    }
}