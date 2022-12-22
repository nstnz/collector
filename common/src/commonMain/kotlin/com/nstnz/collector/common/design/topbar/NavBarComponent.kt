package com.nstnz.collector.common.design.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.primaryText
import com.nstnz.collector.common.design.theme.transparent

@Composable
fun NavBarComponent(
    title: String,
    modifier: Modifier = Modifier,
    titleColor: Color = AppTheme.colors.primaryBackgroundText(),
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    titleIcon: @Composable (() -> Unit)? = null,
) {
    NavBarComponent(
        title = {
            Text(
                text = title,
                style = AppTheme.typography.headingMedium,
                color = titleColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        titleIcon = titleIcon
    )
}

@Composable
private fun NavBarComponent(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = AppTheme.colors.transparent(),
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppTheme.elevations.flat,
    titleIcon: @Composable (() -> Unit)? = null,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        TopAppBar(
            title = {},
            modifier = Modifier
                .align(Alignment.TopCenter),
            navigationIcon = navigationIcon,
            actions = actions,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            elevation = elevation,
        )
        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            titleIcon?.let {
                it.invoke()
                SpacerComponent { x1 }
            }
            title()
        }
    }
}