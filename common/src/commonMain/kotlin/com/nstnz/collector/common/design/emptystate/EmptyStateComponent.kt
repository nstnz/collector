package com.nstnz.collector.common.design.emptystate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.accentColor
import com.nstnz.collector.common.design.theme.secondaryBackgroundText

@Composable
internal fun EmptyStateComponent(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.indents.x7)
) {
    Column(modifier) {
        Icon(
            Icons.Rounded.Savings,
            null,
            modifier = Modifier.size(AppTheme.indents.x6)
                .align(Alignment.CenterHorizontally),
            tint = AppTheme.colors.accentColor()
        )
        SpacerComponent { x2 }
        Text(
            text = text,
            color = AppTheme.colors.secondaryBackgroundText(),
            style = AppTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}