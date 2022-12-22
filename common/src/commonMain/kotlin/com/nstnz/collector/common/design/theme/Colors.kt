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
internal fun Colors.backgroundSecondary(): Color = Color(0x33FFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.gradientPartsTop(): Color = Color(0xFFF4F7FE)

@Composable
@ReadOnlyComposable
internal fun Colors.gradientPartsBottom(): Color = Color(0xFFF4F7FE)

@Composable
@ReadOnlyComposable
internal fun Colors.overlayColor(): Color = Color(0x11000000)

@Composable
@ReadOnlyComposable
internal fun Colors.accentColor(): Color = Color(0xff5B53FF)

@Composable
@ReadOnlyComposable
internal fun Colors.accentPressedColor(): Color = Color(0xff514ae5)


@Composable
@ReadOnlyComposable
internal fun Colors.primaryText(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.secondaryText(): Color = Color(0xCCFFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.hintText(): Color = Color(0x99FFFFFF)

@Composable
@ReadOnlyComposable
internal fun Colors.dividerText(): Color = Color(0x44FFFFFF)


@Composable
@ReadOnlyComposable
internal fun Colors.primaryBackgroundText(): Color = Color(0xFF000000)

@Composable
@ReadOnlyComposable
internal fun Colors.secondaryBackgroundText(): Color = Color(0xCC000000)

@Composable
@ReadOnlyComposable
internal fun Colors.hintBackgroundText(): Color = Color(0x99000000)

@Composable
@ReadOnlyComposable
internal fun Colors.dividerBackgroundText(): Color = Color(0x44000000)


@Composable
@ReadOnlyComposable
internal fun Colors.backgroundSnackBar(): Color = Color(0xFF00FF00)

@Composable
@ReadOnlyComposable
internal fun Colors.backgroundError(): Color = Color(0xFFFF0000)



@Composable
@ReadOnlyComposable
internal fun Colors.transparent(): Color = Color(0x00000000)
