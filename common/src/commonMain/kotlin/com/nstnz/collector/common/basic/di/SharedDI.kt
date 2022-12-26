package com.nstnz.collector.common.basic.di

import com.nstnz.collector.AppDatabase
import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.addcount.di.addCountScreenDi
import com.nstnz.collector.common.feature.addsource.di.addSourceScreenDi
import com.nstnz.collector.common.feature.converter.di.converterScreenDi
import com.nstnz.collector.common.feature.currencies.di.currenciesDi
import com.nstnz.collector.common.feature.editsource.di.editSourceScreenDi
import com.nstnz.collector.common.feature.main.di.mainScreenDi
import com.nstnz.collector.common.feature.settings.di.settingsScreenDi
import com.nstnz.collector.common.feature.source.di.sourceScreenDi
import com.nstnz.collector.common.feature.splash.di.splashScreenDi
import com.squareup.sqldelight.db.SqlDriver
import de.galdigital.preferences.SharedPreferences
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedDI {

    private lateinit var databaseDriver: SqlDriver
    private lateinit var sharedPreferences: SharedPreferences

    fun init(
        databaseDriver: SqlDriver,
        sharedPreferences: SharedPreferences,
    ) {
        println("Launch application")
        SharedDI.databaseDriver = databaseDriver
        SharedDI.sharedPreferences = sharedPreferences
    }

    internal val di = DI {
        bind<Router>() with singleton { Router() }
        bind<CoroutineDispatcher>() with singleton { Dispatchers.Default }
        bind<AppDatabaseQueries>() with singleton {
            val database = AppDatabase(databaseDriver)
            database.appDatabaseQueries
        }
        bind<HttpClient>() with singleton { HttpClient() }
        bind<Json>() with singleton { Json { ignoreUnknownKeys = true } }
        bind<SharedPreferences>() with singleton { sharedPreferences }

        import(editSourceScreenDi)
        import(addSourceScreenDi)
        import(currenciesDi)
        import(addCountScreenDi)
        import(mainScreenDi)
        import(sourceScreenDi)
        import(converterScreenDi)
        import(splashScreenDi)
        import(settingsScreenDi)
    }
}
