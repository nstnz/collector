package com.nstnz.collector.common.feature.addcount.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryText

@Composable
internal fun AddCountScreen(
    viewState: AddCountScreenState,
    onSaveClick: () -> Unit = {},
) {
    GradientScaffold {
        Text(
            text = "ADD COUNT",
            color = AppTheme.colors.primaryText(),
            style = AppTheme.typography.headingMegaLarge
        )
    }
}