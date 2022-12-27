package com.nstnz.collector.common

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return BitmapFactory.decodeByteArray(this, 0, this.size).asImageBitmap()
}

actual fun format(double: Double): String {
    val df = java.text.DecimalFormat("###,###,##0.00")
    df.maximumFractionDigits = 2
    return df.format(double).replace(',', ' ')
}