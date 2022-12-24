package com.nstnz.collector.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

object Colors

@Composable
@ReadOnlyComposable
fun Colors.backgroundPrimary(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.backgroundSecondary(): Color = Color(0x33FFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.gradientPartsTop(): Color = Color(0xFFF4F7FE)

@Composable
@ReadOnlyComposable
fun Colors.gradientPartsBottom(): Color = Color(0xFFF4F7FE)

@Composable
@ReadOnlyComposable
fun Colors.overlayColor(): Color = Color(0x11000000)

@Composable
@ReadOnlyComposable
fun Colors.accentColor(): Color = Color(0xff5B53FF)

@Composable
@ReadOnlyComposable
fun Colors.accentPressedColor(): Color = Color(0xff514ae5)


@Composable
@ReadOnlyComposable
fun Colors.primaryText(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.secondaryText(): Color = Color(0xCCFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.hintText(): Color = Color(0x99FFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.dividerText(): Color = Color(0x44FFFFFF)


@Composable
@ReadOnlyComposable
fun Colors.primaryBackgroundText(): Color = Color(0xFF000000)

@Composable
@ReadOnlyComposable
fun Colors.secondaryBackgroundText(): Color = Color(0xCC000000)

@Composable
@ReadOnlyComposable
fun Colors.hintBackgroundText(): Color = Color(0x99000000)

@Composable
@ReadOnlyComposable
fun Colors.dividerBackgroundText(): Color = Color(0x44000000)


@Composable
@ReadOnlyComposable
fun Colors.backgroundSnackBar(): Color = Color(0xFF00FF00)

@Composable
@ReadOnlyComposable
fun Colors.backgroundError(): Color = Color(0xFFFF0000)



@Composable
@ReadOnlyComposable
fun Colors.transparent(): Color = Color(0x00000000)
