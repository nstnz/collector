package com.nstnz.collector.common.design.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundError
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.primaryText
import com.nstnz.collector.common.design.spacer.SpacerComponent

@Composable
internal fun SnackbarComponent(
    title: String,
    description: String? = null,
    isError: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = AppTheme.indents.x3,
                vertical = AppTheme.indents.x1_5,
            )
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter),
            shape = AppTheme.shapes.x2,
            color = if (isError) {
                AppTheme.colors.backgroundError()
            } else {
                AppTheme.colors.backgroundPrimary()
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.indents.x2),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.headingSmall,
                    color = AppTheme.colors.primaryText()
                )
                description?.let {
                    SpacerComponent { x0_75 }
                    Text(
                        text = it,
                        style = AppTheme.typography.bodySmall,
                        color = AppTheme.colors.primaryText(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}