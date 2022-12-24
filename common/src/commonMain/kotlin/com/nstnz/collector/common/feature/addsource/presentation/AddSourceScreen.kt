package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryText

@Composable
internal fun AddSourceScreen(
    viewState: AddSourceScreenState,
    onSaveClick: () -> Unit = {},
) {
    GradientScaffold {
        Text(
            text = "ADD SOURCE",
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
    }
}