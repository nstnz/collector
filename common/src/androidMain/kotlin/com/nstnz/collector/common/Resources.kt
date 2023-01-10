package com.nstnz.collector.common

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import com.nstnz.collector.common.basic.data.BaseActivity
import org.kodein.di.android.x.AndroidLifecycleScope

@Composable
internal actual fun imageResource(id: String): ImageBitmap {
    val context = Android.context
    val id = context.resources.getIdentifier(id, "drawable", context.packageName)
    return ImageBitmap.Companion.imageResource(id = id)
}

@Composable
internal actual fun imageVector(id: String): ImageVector {
    val context = Android.context
    val id = context.resources.getIdentifier(id, "drawable", context.packageName)
    return ImageVector.vectorResource(id = id)
}

object Android {
    lateinit var context: Context
    lateinit var activity: BaseActivity
}
