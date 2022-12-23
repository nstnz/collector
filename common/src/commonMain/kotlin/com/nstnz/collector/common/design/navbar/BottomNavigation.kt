package com.nstnz.collector.common.design.navbar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.primaryText
import com.nstnz.collector.common.design.theme.transparent

@Composable
internal fun BottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = AppTheme.colors.transparent(),
        contentColor = AppTheme.colors.primaryText(),
        elevation = AppTheme.elevations.flat,
        shape = AppTheme.shapes.x3,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.indents.x9)
        )

        Row(
            modifier
                .fillMaxWidth()
                .height(AppTheme.indents.x9)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Composable
internal fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val ripple = rememberRipple(
        bounded = false,
        color = AppTheme.colors.primaryText()
    )

    Column(
        modifier
            .align(CenterVertically)
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gradient = AppTheme.gradients.backgroundScreen()
        val iconModifier = if (selected) {
            Modifier
        } else {
            Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                }
        }

        Box() {
            /*Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = iconModifier,
                tint = AppTheme.colors.primaryText()
            )*/
        }

        SpacerComponent { x0_5 }
        Text(
            text = text,
            style = AppTheme.typography.labelXsmall,
            color = if (selected) AppTheme.colors.primaryBackgroundText() else AppTheme.colors.primaryText()
        )
    }
}