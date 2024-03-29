package com.nstnz.collector.common.basic.router

internal const val Arg1 = "Arg1"
internal const val Arg2 = "Arg2"
internal const val Arg3 = "Arg3"

internal enum class Routes(val floating: Boolean = false) {
    Main,
    Source,
    Converter,
    Currencies,
    Welcome,
    Settings,
    Splash,
    AddSource,
    AddCount,
    EditSource,
    EditCount,
    EditCountMode(floating = true),
}