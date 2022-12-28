package com.nstnz.collector.common.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreen
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreenState
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreen
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreenState

@Composable
@Preview
private fun EditCountScreenPreview() {
    AppTheme {
        EditCountScreen(
            EditCountScreenState.Default(
                getMockSourceListDomainModel().sources.first().counts.first(),
                getMockCurrencyDomainModel("USD"),
                sum = "35345"
            )
        )
    }
}