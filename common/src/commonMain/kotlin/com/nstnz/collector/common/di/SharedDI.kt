package com.nstnz.collector.common.di

import com.nstnz.collector.AppDatabase
import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.feature.converter.di.converterScreenDi
import com.nstnz.collector.common.feature.currencies.di.currenciesDi
import com.nstnz.collector.common.feature.main.di.mainScreenDi
import com.nstnz.collector.common.feature.source.di.sourceScreenDi
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedDI {

    private lateinit var databaseDriver: SqlDriver

    fun init(
        databaseDriver: SqlDriver
    ) {
        println("Launch application")
        this.databaseDriver = databaseDriver
    }

    internal val di = DI {
        bind<Router>() with singleton { Router() }
        bind<CoroutineDispatcher>() with singleton { Dispatchers.Default }
        bind<AppDatabaseQueries>() with singleton {
            val database = AppDatabase(databaseDriver)
            database.appDatabaseQueries
        }
        bind<HttpClient>() with provider { HttpClient() }
        bind<Json>() with provider { Json { ignoreUnknownKeys = true } }

        import(currenciesDi)
        import(mainScreenDi)
        import(sourceScreenDi)
        import(converterScreenDi)
    }
}
