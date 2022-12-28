package com.nstnz.collector.common.feature.splash.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent
import com.nstnz.collector.common.basic.texts.MainScreen_Title
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
                        text = "Collector",
                        color = AppTheme.colors.primaryBackgroundText(),
                        style = AppTheme.typography.headingMegaLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    SpacerComponent { x0_5 }
                    Text(
                        text = "Your money tracker",
                        color = AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                }
            }
        }
    }
}