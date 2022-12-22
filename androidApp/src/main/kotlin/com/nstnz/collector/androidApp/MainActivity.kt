package com.nstnz.collector.androidApp

import android.graphics.Color
import android.os.Bundle
import com.nstnz.collector.common.Android
import com.nstnz.collector.common.RootView
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView()
        }
    }
}
