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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundPrimary())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.indents.x0_125)
                .background(AppTheme.colors.backgroundSecondary())
        )
        SpacerComponent { x0_75 }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.indents.x2)
                .align(Alignment.CenterHorizontally)
        ) {
            items.forEach {
                Column(Modifier
                    .weight(1f)
                    .noEffectsClickable { it.onClick() }) {
                    Icon(
                        it.icon, null,
                        modifier = Modifier.align(Alignment.CenterHorizontally).size(AppTheme.indents.x3_5),
                        tint = if (it.selected)
                            AppTheme.colors.accentColor() else AppTheme.colors.secondaryBackgroundText()
                    )
                    SpacerComponent { x0_5 }

                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = it.text,
                        color = if (it.selected)
                            AppTheme.colors.accentColor() else AppTheme.colors.secondaryBackgroundText(),
                        style = AppTheme.typography.bodySmall
                    )
                }
            }
        }
        SpacerComponent { x1 }
    }
}


@Composable
private fun BottomNavigationItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector,
) {

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