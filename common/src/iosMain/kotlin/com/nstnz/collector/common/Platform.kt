package com.nstnz.collector.common

import platform.UIKit.*
import platform.Foundation.NSUUID

actual class Platform actual constructor() {
    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun randomUUID(): String = NSUUID().UUIDString()