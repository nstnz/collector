package com.nstnz.collector.androidApp

import android.app.Application
import com.nstnz.collector.common.Android
import com.nstnz.collector.common.basic.data.db.dao.DatabaseDriverFactory
import com.nstnz.collector.common.di.SharedDI

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Android.context = this
        Thread.setDefaultUncaughtExceptionHandler(TopExceptionHandler())
        SharedDI.init(
            databaseDriver = DatabaseDriverFactory(this).createDriver()
        )
    }
}

private class TopExceptionHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        println(e.toString())
    }
}