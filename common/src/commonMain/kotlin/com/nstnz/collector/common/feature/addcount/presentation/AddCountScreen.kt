package com.nstnz.collector.common.feature.addcount.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.design.topbar.NavBarComponent

@Composable
internal fun AddCountScreen(
    viewState: AddCountScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onSelectSourceClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = { DefaultNavComponent(onBackClick) },
        bottomBar = {
            BottomButtonComponent(
                text = "Ololo",
                onClick = onSaveClick
            )
        }
    ) {
        when (viewState) {
            is AddCountScreenState.Default -> AddCountScreenDefaultState(
                viewState
            )
            AddCountScreenState.Loading -> {}
        }
    }
}


@Composable
private fun AddCountScreenDefaultState(
    viewState: AddCountScreenState.Default,
) {
    CardComponent {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppTheme.indents.x3)
        ) {
            SpacerComponent { x3 }
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
            SpacerComponent { x3 }
            TextInputComponent(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "0",
                label = "Enter sum",
                value = TextFieldValue(viewState.sum),
                onValueChange = {},
            )
            SpacerComponent { x2 }
            TextSelectorComponent(
                modifier = Modifier.fillMaxWidth(),
                label = "Choose currency",
                text = viewState.currency.code
            )
            SpacerComponent { x2 }
            TextSelectorComponent(
                modifier = Modifier.fillMaxWidth(),
                text = viewState.sourceModel?.name.orEmpty(),
                label = "Choose source",
            )
            SpacerComponent { x2 }
            TextSelectorComponent(
                modifier = Modifier.fillMaxWidth(),
                text = viewState.fund?.name.orEmpty(),
                label = "Choose account",
            )
            SpacerComponent { x4 }
        }
    }
}