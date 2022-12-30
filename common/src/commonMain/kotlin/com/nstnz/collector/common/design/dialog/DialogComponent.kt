package com.nstnz.collector.common.design.dialog

import androidx.compose.animation.*
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextAlign
import com.nstnz.collector.common.App
import com.nstnz.collector.common.design.button.PrimaryButtonComponent
import com.nstnz.collector.common.design.button.SecondaryButtonComponent
import com.nstnz.collector.common.design.card.CardComponent
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DialogComponent(
    title: String,
    description: String,
    okButtonText: String,
    middleButtonText: String? = null,
    cancelButtonText: String? = null,
    okButtonClick: () -> Unit = {},
    middleButtonClick: () -> Unit = {},
    cancelButtonClick: () -> Unit = {},
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.overlay2Color())
            .noEffectsClickable {
                visible = false
                cancelButtonClick()
            }
    ) {
        AnimatedVisibility(
            visible = visible,
            modifier = Modifier
                .align(Alignment.Center),
            enter = fadeIn(animationSpec = tween(durationMillis = 100, easing = EaseIn)),
            exit = fadeOut(),
        ) {
            CardComponent(
                elevation = AppTheme.elevations.secondaryCard,
                shape = AppTheme.shapes.x2,
                modifier = Modifier
                    .padding(AppTheme.indents.x4)
                    .padding(bottom = AppTheme.indents.x6)
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier.fillMaxWidth()
                        .padding(horizontal = AppTheme.indents.x3, vertical = AppTheme.indents.x2)
                ) {
                    Text(
                        text = title,
                        style = AppTheme.typography.headingMedium,
                        color = AppTheme.colors.primaryBackgroundText()
                    )
                    SpacerComponent { x0_75 }
                    Text(
                        text = description,
                        style = AppTheme.typography.bodyMedium,
                        color = AppTheme.colors.secondaryBackgroundText(),
                    )
                    SpacerComponent { x4 }

                    if (middleButtonText.isNullOrEmpty() && cancelButtonText.isNullOrEmpty()) {
                        PrimaryButtonComponent(
                            text = okButtonText,
                            onClick = {
                                cancelButtonClick()
                                okButtonClick()
                            }
                        )
                    } else if (middleButtonText.isNullOrEmpty() && !cancelButtonText.isNullOrEmpty()) {
                        Row(Modifier.fillMaxWidth()) {
                            PrimaryButtonComponent(
                                modifier = Modifier.weight(1f),
                                text = okButtonText,
                                onClick = {
                                    cancelButtonClick()
                                    okButtonClick()
                                }
                            )
                            SpacerComponent { x1 }
                            SecondaryButtonComponent(
                                modifier = Modifier.weight(1f),
                                text = cancelButtonText,
                                onClick = {
                                    visible = false
                                    cancelButtonClick()
                                }
                            )
                        }
                    } else if (!middleButtonText.isNullOrEmpty() && !cancelButtonText.isNullOrEmpty()) {
                        PrimaryButtonComponent(
                            text = okButtonText,
                            onClick = {
                                cancelButtonClick()
                                okButtonClick()
                            }
                        )
                        SpacerComponent { x1 }
                        PrimaryButtonComponent(
                            text = middleButtonText,
                            onClick = {
                                cancelButtonClick()
                                middleButtonClick()
                            }
                        )
                        SpacerComponent { x3 }
                        SecondaryButtonComponent(
                            text = cancelButtonText,
                            onClick = {
                                visible = false
                                cancelButtonClick()
                            }
                        )
                    }
                }
            }
        }
    }
}