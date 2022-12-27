package com.nstnz.collector.common.design.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundPrimary
import com.nstnz.collector.common.design.theme.overlayColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GradientModalBottomSheetScaffold(
    gradient: Brush = AppTheme.gradients.backgroundScreen(),
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable ColumnScope.() -> Unit = {},
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    ),
    overflowBackground: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {

        overflowBackground?.invoke()

        ModalBottomSheetLayout(
            sheetContent = {
                bottomSheet()
            },
            sheetElevation = AppTheme.elevations.flat,
            sheetShape = AppTheme.shapes.x4_5_top,
            sheetState = bottomSheetState,
            sheetBackgroundColor = AppTheme.colors.backgroundPrimary(),
            scrimColor = AppTheme.colors.overlayColor().copy(alpha = 0.3f),
        ) {
            ScaffoldComponent(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                content = content,
                backgroundColor = Color.Transparent,
                scaffoldState = scaffoldState,
                snackbarHost = { },
                dialog = {}
            )
        }
    }
}
