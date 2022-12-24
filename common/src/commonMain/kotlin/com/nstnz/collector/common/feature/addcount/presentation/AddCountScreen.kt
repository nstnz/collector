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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.topbar.NavBarComponent

@Composable
internal fun AddCountScreen(
    viewState: AddCountScreenState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            NavBarComponent(
                modifier = Modifier.background(AppTheme.colors.backgroundPrimary()),
                title = "",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Rounded.ArrowBackIosNew,
                            null,
                            modifier = Modifier.size(AppTheme.indents.x3_5),
                            tint = AppTheme.colors.primaryBackgroundText()
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomButtonComponent(
                text = "Ololo",
                onClick = onSaveClick
            )
        }
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
                    value = TextFieldValue(""),
                    onValueChange = {},
                )
                SpacerComponent { x2 }
                TextInputComponent(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "0",
                    label = "Choose currency",
                    value = TextFieldValue(""),
                    onValueChange = {},
                )
                SpacerComponent { x2 }
                TextInputComponent(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Source",
                    label = "Choose source",
                    value = TextFieldValue(""),
                    onValueChange = {},
                )
                SpacerComponent { x2 }
                TextInputComponent(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Account",
                    label = "Choose account",
                    value = TextFieldValue(""),
                    onValueChange = {},
                )
                SpacerComponent { x4 }
            }
        }
    }
}