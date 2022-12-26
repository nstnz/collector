package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreen
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreenState

@Composable
@Preview
private fun EditSourceScreenPreview() {
    AppTheme {
        EditSourceScreen(
            EditSourceScreenState.Default(
                "Kek", getMockCurrencyDomainModel()
            )
        )
    }
}