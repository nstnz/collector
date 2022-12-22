package com.nstnz.collector.common.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

object Colors

@Composable
@ReadOnlyComposable
fun Colors.backgroundSheetPrimary(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.backgroundSheetSecondary(): Color = Color(0x33FFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.gradientPartsTop(): Color = Color(0xFFA5A4CB)

@Composable
@ReadOnlyComposable
fun Colors.gradientPartsBottom(): Color = Color(0xFF8989BB)

@Composable
@ReadOnlyComposable
fun Colors.backgroundOverlayDimming(): Color = Color(0x11000000)

@Composable
@ReadOnlyComposable
fun Colors.backgroundSnackBar(): Color = Color(0xFF00FF00)

@Composable
@ReadOnlyComposable
fun Colors.backgroundError(): Color = Color(0xFFFF0000)

@Composable
@ReadOnlyComposable
fun Colors.primaryText(): Color = Color(0xFF000000)

@Composable
@ReadOnlyComposable
fun Colors.secondaryBackgroundText(): Color = Color(0xCCFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.hintBackgroundText(): Color = Color(0x99FFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.dividerBackgroundText(): Color = Color(0x44FFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.primaryBackgroundText(): Color = Color(0xFFFFFFFF)

@Composable
@ReadOnlyComposable
fun Colors.transparent(): Color = Color(0x00000000)
