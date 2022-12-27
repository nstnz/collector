package com.nstnz.collector.common.design.title

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryBackgroundText

@Composable
internal fun TitleComponent(
    title: String,
    onAddClick: () -> Unit = {}
) {
    Row(
        Modifier.fillMaxWidth().padding(start = AppTheme.indents.x3, end = AppTheme.indents.x1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colors.primaryBackgroundText(),
            style = AppTheme.typography.headingMedium,
            maxLines = 1
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onAddClick) {
            Icon(
                Icons.Rounded.AddCircleOutline,
                null,
                modifier = Modifier.size(AppTheme.indents.x3),
                tint = AppTheme.colors.primaryBackgroundText()
            )
        }
    }
}