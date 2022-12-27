package com.nstnz.collector.common

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return org.jetbrains.skia.Image.makeFromEncoded(this).toComposeImageBitmap()
}

actual fun format(double: Double): String {
    val formatter = NSNumberFormatter()
    formatter.minimumFractionDigits = 0u
    formatter.maximumFractionDigits = 2u
    formatter.numberStyle = 1u //Decimal
    return formatter.stringFromNumber(NSNumber(double))!!
}