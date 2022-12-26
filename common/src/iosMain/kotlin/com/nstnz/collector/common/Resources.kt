package com.nstnz.collector.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy

@Composable
internal actual fun imageResource(id: String): ImageBitmap {
    // TODO: maybe use something more efficient
    // TODO: maybe https://github.com/touchlab/DroidconKotlin/blob/main/shared-ui/src/iosMain/kotlin/co/touchlab/droidcon/ui/util/ToSkiaImage.kt
    val image = UIImage.imageNamed(id)!!
    val data = UIImagePNGRepresentation(image)!!
    val byteArray = ByteArray(data.length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), data.bytes, data.length)
        }
    }
    return org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

@Composable
internal actual fun imageVector(id: String): ImageVector {
    return ImageVector.Builder("", 0.dp, 0.dp, 0f,
        0f, Color.Cyan, BlendMode.Clear, false).build()
}
