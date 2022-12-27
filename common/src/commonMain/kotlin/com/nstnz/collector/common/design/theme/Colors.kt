package com.nstnz.collector.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

internal object Colors

@Composable
@ReadOnlyComposable
internal fun Colors.backgroundPrimary(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.backgroundSecondary(): Color = Color(0xFFF2F7FA)

@Composable
@ReadOnlyComposable
internal fun Colors.gradientPartsTop(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.gradientPartsBottom(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.overlayColor(): Color = Color(0x22000000)

@Composable
@ReadOnlyComposable
internal fun Colors.accentColor(): Color = Color(0xff18A0FB)

@Composable
@ReadOnlyComposable
internal fun Colors.accent2Color(): Color = Color(0xffFF521C)

@Composable
@ReadOnlyComposable
internal fun Colors.primaryText(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.secondaryText(): Color = Color(0xCCFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.primaryBackgroundText(): Color = Color(0xFF1F1428)

@Composable
@ReadOnlyComposable
internal fun Colors.secondaryBackgroundText(): Color = Color(0xFF656770)

@Composable
@ReadOnlyComposable
internal fun Colors.backgroundError(): Color = Color(0xFFFFEEED)

@Composable
@ReadOnlyComposable
internal fun Colors.backgroundSuccess(): Color = Color(0xFF28923D)


@Composable
@ReadOnlyComposable
internal fun Colors.transparent(): Color = Color(0x00000000)


@Composable
@ReadOnlyComposable
internal fun Colors.hintBackgroundText(): Color = Color(0x00000000)
