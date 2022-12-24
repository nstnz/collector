package com.nstnz.collector.common.design.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun Colors.toMaterialColors(): androidx.compose.material.Colors =
	androidx.compose.material.Colors(
		primary = primaryText(),
		primaryVariant = primaryText(),
		secondary = primaryText(),
		secondaryVariant = primaryText(),
		background = backgroundPrimary(),
		surface = backgroundPrimary(),
		error = backgroundError(),
		onPrimary = primaryText(),
		onSecondary = primaryText(),
		onBackground = primaryBackgroundText(),
		onSurface = primaryBackgroundText(),
		onError = backgroundError(),
		isLight = true,
	)

@Composable
fun Modifier.noEffectsClickable(
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	onClick: () -> Unit,
) =
	this.clickable(
		onClick = onClick,
		interactionSource = interactionSource,
		indication = null
	)