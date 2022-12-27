package com.nstnz.collector.common.feature.addsource.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nstnz.collector.common.design.scaffold.BottomSheetComponent
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenState
import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel

@Composable
internal fun AddSourceScreen(
    viewState: AddSourceScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: (String) -> Unit = {},
    onChangeName: (String) -> Unit = {},
    onChangeDefaultCurrency: (CurrencyDomainModel) -> Unit = {},
) {
    BottomSheetComponent(
        title = "Добавить источник"
    ) {
        Text(
            text = "JHKdhkjahdkjshd aasd",
            color = AppTheme.colors.primaryBackgroundText(),
            style = AppTheme.typography.headingMedium
        )
        SpacerComponent { x0_5 }
        Text(
            text = "JHKdhkjahdkjshd as dkhjsd klfsdkfsdfhlsdklsf hasd",
            color = AppTheme.colors.secondaryBackgroundText(),
            style = AppTheme.typography.bodyMedium
        )
    }
}