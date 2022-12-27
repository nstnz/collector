package com.nstnz.collector.common.design.swipedismiss

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundError
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import kotlin.math.roundToInt

private const val ANIMATION_DURATION = 300
private const val MIN_DRAG_AMOUNT = -6

@Composable
internal fun SwipeDismissComponent(
    modifier: Modifier,
    onDeleteClick: () -> Unit =  {},
    content: @Composable () -> Unit
) {
    var isRevealed by remember { mutableStateOf(false) }
    val cardOffset = with(LocalDensity.current) { AppTheme.indents.x9.toPx() }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState)
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset else 0f },
    )

    Box(modifier.background(AppTheme.colors.backgroundError())) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.backgroundError())
                .padding(end = AppTheme.indents.x3)
                .noEffectsClickable { onDeleteClick() }
        ) {
            Icon(
                Icons.Rounded.DeleteOutline,
                contentDescription = null,
                tint = AppTheme.colors.secondaryBackgroundText(),
                modifier = Modifier.padding(top = AppTheme.indents.x1_5).size(AppTheme.indents.x3)
                    .align(Alignment.CenterEnd)
            )
        }
        Box(
            Modifier.fillMaxSize()
                .offset { IntOffset(-1 * offsetTransition.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        when {
                            dragAmount <= MIN_DRAG_AMOUNT -> isRevealed = true
                            dragAmount > MIN_DRAG_AMOUNT -> isRevealed = false
                        }
                    }
                }) {
            Box(Modifier.fillMaxSize().background(AppTheme.colors.backgroundPrimary())) {
                content()
            }
        }
    }
}