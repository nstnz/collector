package com.nstnz.collector.common.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.nstnz.collector.common.design.button.BottomButtonComponent
import com.nstnz.collector.common.design.button.PrimaryButtonComponent
import com.nstnz.collector.common.design.input.TextInputComponent
import com.nstnz.collector.common.design.input.internal.TextInputState
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme

@Composable
@Preview
private fun ComponentsPreview() {
    AppTheme {
        GradientScaffold {
            Column(
                Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.indents.x3)
                ) {
                    PrimaryButtonComponent(text = "Ololo", onClick = { })
                    SpacerComponent { x2 }

                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Placeholder1",
                        value = TextFieldValue(""),
                        onValueChange = {}
                    )
                    SpacerComponent { x2 }
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "",
                        value = TextFieldValue("123"),
                        onValueChange = {},
                        hasClearButton = true
                    )
                    SpacerComponent { x2 }
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "",
                        label = "Hello",
                        value = TextFieldValue("Text input max overfill content Y Text input max over"),
                        onValueChange = {},
                    )
                    SpacerComponent { x2 }
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "",
                        value = TextFieldValue("456"),
                        onValueChange = {},
                        hint = "Hint text",
                    )
                    SpacerComponent { x2 }
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Placeholder",
                        value = TextFieldValue("789"),
                        onValueChange = {},
                        textFieldState = TextInputState.Error("Error text")
                    )
                    SpacerComponent { x2 }
                    TextInputComponent(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Placeholder",
                        value = TextFieldValue("789"),
                        onValueChange = {},
                    )
                    SpacerComponent { x2 }

                }
                Spacer(modifier = Modifier.weight(1f))
                BottomButtonComponent(text = "Ololo", onClick = { })
            }
        }
    }
}

@Composable
@Preview
private fun ComponentsPreview2() {
    AppTheme {
        GradientScaffold(
            bottomBar = {
                NavigationBarComponent(
                    mainTabClick = { },
                    settingsTabClick = { },
                    converterTabClick = { },
                    mainTabSelected = true
                )
            }
        ) {

        }
    }
}