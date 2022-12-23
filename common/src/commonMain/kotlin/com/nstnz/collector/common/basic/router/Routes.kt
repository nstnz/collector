package com.nstnz.collector.common.basic.router

internal const val Arg1 = "Arg1"
internal const val Arg2 = "Arg2"
internal const val Arg3 = "Arg3"

internal enum class Routes {
    Main,
    Source,
    Converter,
    Settings;

    fun getRoute(): String {
        return this.name + "?$Arg1={$Arg1}&$Arg2={$Arg2}&$Arg3={$Arg3}"
    }
}