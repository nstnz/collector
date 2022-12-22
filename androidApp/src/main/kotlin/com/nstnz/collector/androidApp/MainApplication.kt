package com.nstnz.collector.androidApp

import android.app.Application
import com.nstnz.collector.common.Android
import com.nstnz.collector.common.di.SharedDI

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Android.context = this
        SharedDI.init()
    }
}