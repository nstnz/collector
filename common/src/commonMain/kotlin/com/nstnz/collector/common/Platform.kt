package com.nstnz.collector.common

import org.kodein.di.bindings.Scope

expect class Platform() {
    val platform: String
}

expect fun randomUUID(): String