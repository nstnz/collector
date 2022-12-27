package com.nstnz.collector.common.design.scaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.App
import com.nstnz.collector.common.design.snackbar.SnackbarComponent
import com.nstnz.collector.common.design.snackbar.SnackbarHost
import com.nstnz.collector.common.design.snackbar.SnackbarHostState
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.overlayColor
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import kotlinx.coroutines.launch

@Composable
internal fun BottomSheetComponent(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        Modifier.fillMaxSize().background(AppTheme.colors.overlayColor()),
        contentAlignment = BottomCenter
    ) {
        Surface(
            Modifier.fillMaxWidth(),
            shape = AppTheme.shapes.x4_5_top,
            color = AppTheme.colors.backgroundPrimary(),
            elevation = AppTheme.elevations.secondaryCard
        ) {
            Column(
                Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x1),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(Modifier.fillMaxWidth()) {
                    IconButton(modifier = Modifier.size(AppTheme.indents.x7_5), onClick = {}) {
                        Icon(
                            Icons.Rounded.Close, null,
                            modifier = Modifier.size(AppTheme.indents.x3),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        modifier = Modifier.align(CenterVertically),
                        text = title,
                        style = AppTheme.typography.headingMedium,
                        color = AppTheme.colors.primaryBackgroundText(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(modifier = Modifier.size(AppTheme.indents.x7_5), onClick = {}) {
                        Icon(
                            Icons.Rounded.Done, null,
                            modifier = Modifier.size(AppTheme.indents.x3),
                            tint = AppTheme.colors.accentColor()
                        )
                    }
                }

                SpacerComponent { x2 }
                Column(Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x2)) {
                    content()
                }
                SpacerComponent { x4 }
            }

        }
    }
}
