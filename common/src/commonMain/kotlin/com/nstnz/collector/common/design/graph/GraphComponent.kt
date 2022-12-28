package com.nstnz.collector.common.design.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.*
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.accentColor
import com.nstnz.collector.common.design.theme.hintBackgroundText
import com.nstnz.collector.common.design.theme.primaryBackgroundText

@Composable
internal fun GraphComponent(
    points: List<Double>,
    modifier: Modifier = Modifier,
    graphColor: Color = AppTheme.colors.accentColor(),
    width: Dp,
    text: String
) {
    val lineColor = AppTheme.colors.hintBackgroundText()
    val height = 120.dp
    val originalHeight = height + AppTheme.indents.x5
    val padding = AppTheme.indents.x1_5
    val heightPx = with(LocalDensity.current) { originalHeight.toPx() }
    val widthPx = with(LocalDensity.current) { width.toPx() }
    val strokePx = with(LocalDensity.current) { AppTheme.indents.x0_25.toPx() }
    val spacing = widthPx / (points.size - 1).toFloat()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(originalHeight)
    ) {
        if (points.isNotEmpty() && points.any { it > 0.0 }) {
            val pointsPairs = points.map {
                Pair(it, with(LocalDensity.current) {
                    val h = (height - padding * 2).toPx()
                    h - h * (it / points.max()).toFloat() + padding.toPx() + AppTheme.indents.x5.toPx()
                })
            }
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(originalHeight)
            ) {
                val normX = mutableListOf<Float>()
                val normY = mutableListOf<Float>()

                val strokePath = Path().apply {
                    pointsPairs.forEachIndexed { i, pointPair ->
                        val currentX = i * spacing
                        val previousX = ((i - 1) * spacing).takeIf { it > 0 } ?: 0f
                        if (i == 0) {
                            moveTo(previousX, pointsPairs[i].second)
                        }

                        normX.add(currentX)
                        normY.add(pointPair.second)

                        val conX1 = (previousX + currentX) / 2f
                        val conX2 = (previousX + currentX) / 2f

                        val conY1 = if (i > 0) pointsPairs[i - 1].second else pointsPairs[i].second
                        val conY2 = pointsPairs[i].second
                        cubicTo(
                            x1 = conX1,
                            y1 = conY1,
                            x2 = conX2,
                            y2 = conY2,
                            x3 = currentX,
                            y3 = pointsPairs[i].second
                        )
                    }
                }

                val line: (Float) -> Unit = {
                    drawLine(
                        start = Offset(it, 0f),
                        end = Offset(it, heightPx),
                        brush = SolidColor(lineColor),
                        strokeWidth = strokePx,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(3f, 18f)
                        )
                    )
                }

                (normX.indices).filterIndexed { index, i -> index % 10 == 0 || index == normX.indices.last }
                    .forEach {
                        line(normX[it])
                    }

                drawPath(
                    path = strokePath,
                    color = graphColor,
                    style = Stroke(
                        width = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            Text(
                text = text,
                style = AppTheme.typography.headingMedium,
                color = AppTheme.colors.primaryBackgroundText(),
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(bottom = AppTheme.indents.x1)
            )
            Row(Modifier.padding(AppTheme.indents.x0_5)) {
                Box(
                    Modifier.size(AppTheme.indents.x3)
                        .background(AppTheme.colors.secondaryBackgroundText(), AppTheme.shapes.x1)
                ) {
                    Icon(
                        Icons.Rounded.CalendarMonth,
                        null,
                        modifier = Modifier.size(AppTheme.indents.x2)
                            .align(Alignment.Center),
                        tint = AppTheme.colors.backgroundPrimary()
                    )
                }
                SpacerComponent { x1 }
                Text(
                    text = "Курс за год",
                    style = AppTheme.typography.bodySmall,
                    color = AppTheme.colors.primaryBackgroundText(),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

        } else {
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(originalHeight)
            ) {
                val normX = mutableListOf<Float>()

                points.forEachIndexed { i, d ->
                    val currentX = i * spacing
                    normX.add(currentX)
                }

                val line: (Float) -> Unit = {
                    drawLine(
                        start = Offset(it, 0f),
                        end = Offset(it, heightPx),
                        brush = SolidColor(lineColor),
                        strokeWidth = strokePx,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(3f, 18f)
                        )
                    )
                }

                (normX.indices).forEach {
                    line(normX[it])
                }
            }

            Text(
                text = "Нет данных",
                style = AppTheme.typography.headingMedium,
                color = AppTheme.colors.primaryBackgroundText(),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}