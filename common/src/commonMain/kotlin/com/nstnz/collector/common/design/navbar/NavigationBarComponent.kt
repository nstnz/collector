package com.nstnz.collector.common.design.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.basic.texts.ConverterScreen_Title
import com.nstnz.collector.common.basic.texts.MainScreen_Title
import com.nstnz.collector.common.basic.texts.SettingsScreen_Title

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
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = AppTheme.indents.x2,
                end = AppTheme.indents.x2,
                bottom = AppTheme.indents.x2
            )
            .background(
                AppTheme.gradients.secondaryBackgroundScreen(),
                shape = AppTheme.shapes.x3
            )
    ) {
        SpacerComponent { x1 }
        items.forEach { item ->
            BottomNavigationItem(
                //icon = imageVector(item.icon),
                text = item.text,
                selected = item.selected,
                onClick = item.onClick
            )
        }
        SpacerComponent { x1 }
    }
}

internal interface NavigationItem {
    val selected: Boolean
    val onClick: () -> Unit
    val icon: String
    val text: String
}

private data class MainNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: String = "",
    override val text: String = MainScreen_Title,
) : NavigationItem

private data class ConverterNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: String = "",
    override val text: String = ConverterScreen_Title,
) : NavigationItem

private data class SettingsNavigationItem(
    override val selected: Boolean,
    override val onClick: () -> Unit = {},
    override val icon: String = "",
    override val text: String = SettingsScreen_Title,
) : NavigationItem