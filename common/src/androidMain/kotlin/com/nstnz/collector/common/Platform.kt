package com.nstnz.collector.common

import java.util.*

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun randomUUID() = UUID.randomUUID().toString()