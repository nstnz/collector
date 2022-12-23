package com.nstnz.collector.common.basic.router

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

internal fun Navigator.navigate(routes: Routes, options: NavOptions? = null, vararg args: Any) {
    this.navigate(
        routes.name + "?$Arg1=${args.getOrNull(0)}" +
                "&$Arg2=${args.getOrNull(1) ?: ""}" +
                "&$Arg3=${args.getOrNull(2) ?: ""}",
        options
    )
}