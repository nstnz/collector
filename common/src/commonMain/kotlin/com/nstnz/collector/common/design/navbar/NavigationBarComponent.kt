package com.nstnz.collector.common.design.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import com.nstnz.collector.common.App
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.basic.texts.ConverterScreen_Title
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.basic.texts.SettingsScreen_Title
import com.nstnz.collector.common.design.theme.*

@Composable
internal fun NavigationBarComponent(
    mainTabSelected: Boolean = false,
    converterTabSelected: Boolean = false,
    settingsTabSelected: Boolean = false,
    mainTabClick: () -> Unit,
    converterTabClick: () -> Unit,
    settingsTabClick: () -> Unit,
) {
    NavigationBarComponent(
        items = listOf(
            ConverterNavigationItem(selected = converterTabSelected, onClick = converterTabClick),
            MainNavigationItem(selected = mainTabSelected, onClick = mainTabClick),
            SettingsNavigationItem(selected = settingsTabSelected, onClick = settingsTabClick),
        )
    )
}

@Composable
private fun NavigationBarComponent(
    items: List<NavigationItem>,
) {
    Surface(
        shape = AppTheme.shapes.x4_top,
        elevation = AppTheme.elevations.card
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.indents.x7_5)
                .background(
                    AppTheme.gradients.accentGradient(),
                    shape = AppTheme.shapes.x4_top
                )
                .padding(horizontal = AppTheme.indents.x2)
        ) {
            items.forEach {
                Box(Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .noEffectsClickable { it.onClick() }) {
                    BottomNavigationItem(
                        it.selected,
                        Modifier.align(Alignment.Center),
                        it.icon
                    )
                }
            }
        }
    }
}


@Composable
private fun BottomNavigationItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector,
) {
    Icon(
        icon, null,
        modifier = modifier.size(if (selected) AppTheme.indents.x4_5 else AppTheme.indents.x3_5),
        tint = if (selected) AppTheme.colors.primaryText() else AppTheme.colors.secondaryText()
    )
}

internal interface NavigationItem {
    val selected: Boolean
    val onClick: () -> Unit
    val icon: ImageVector
    val text: String
}

private data class MainNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: ImageVector = Icons.Rounded.AccountBalanceWallet,
    override val text: String = MainScreen_Title,
) : NavigationItem

private data class ConverterNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: ImageVector = Icons.Rounded.MonetizationOn,
    override val text: String = ConverterScreen_Title,
) : NavigationItem

private data class SettingsNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: ImageVector = Icons.Rounded.Settings,
    override val text: String = SettingsScreen_Title,
) : NavigationItem