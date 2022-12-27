package com.nstnz.collector.common.design.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.overlayColor
import com.nstnz.collector.common.design.theme.primaryBackgroundText

@Composable
internal fun BottomSheetComponent(
    title: String,
    description: String? = null,
    onCloseClick: () -> Unit = {},
    onOkClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        Modifier.fillMaxSize().background(AppTheme.colors.overlayColor()).noEffectsClickable {
            onCloseClick()
        },
        contentAlignment = TopCenter
    ) {
        Surface(
            Modifier.padding(top = AppTheme.indents.x9).fillMaxSize(),
            shape = AppTheme.shapes.x4_5_top,
            color = AppTheme.colors.backgroundPrimary(),
            elevation = AppTheme.elevations.secondaryCard
        ) {
            Column(
                Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x1),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier.size(AppTheme.indents.x7_5),
                        onClick = onCloseClick
                    ) {
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
                    IconButton(
                        modifier = Modifier.size(AppTheme.indents.x7_5),
                        onClick = onOkClick
                    ) {
                        Icon(
                            Icons.Rounded.Done, null,
                            modifier = Modifier.size(AppTheme.indents.x3),
                            tint = AppTheme.colors.accentColor()
                        )
                    }
                }

                SpacerComponent { x2 }
                Column(Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x2)) {
                    description?.let {
                        Text(
                            text = description,
                            color = AppTheme.colors.secondaryBackgroundText(),
                            style = AppTheme.typography.bodyMedium
                        )
                        SpacerComponent { x2 }
                    }
                    content()
                }
                SpacerComponent { x4 }
            }

        }
    }
}
