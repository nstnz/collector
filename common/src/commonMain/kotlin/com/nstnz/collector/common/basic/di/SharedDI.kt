package com.nstnz.collector.common.basic.di

import com.nstnz.collector.AppDatabase
import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.basic.texts.En_Strings
import com.nstnz.collector.common.basic.texts.IStrings
import com.nstnz.collector.common.feature.core.di.coreDi
import com.nstnz.collector.common.feature.core.di.viewModelsDi
import com.nstnz.collector.common.feature.currencies.di.currenciesDi
import com.nstnz.collector.common.feature.source.di.sourceScreenDi
import com.squareup.sqldelight.db.SqlDriver
import de.galdigital.preferences.SharedPreferences
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

internal val strings: IStrings by SharedDI.di.instance()

@ThreadLocal
object SharedDI {

    private lateinit var databaseDriver: SqlDriver
    private lateinit var sharedPreferences: SharedPreferences

    fun initializeWithParams(
        databaseDriver: SqlDriver,
        sharedPreferences: SharedPreferences,
    ) {
        println("Launch application")
        SharedDI.databaseDriver = databaseDriver
        SharedDI.sharedPreferences = sharedPreferences
    }

    internal val di = DI {
        bind<Router>() with singleton { Router() }
        bind<IStrings>() with singleton { En_Strings }
        bind<CoroutineDispatcher>() with singleton { Dispatchers.Default }
        bind<AppDatabaseQueries>() with singleton {
            val database = AppDatabase(databaseDriver)
            database.appDatabaseQueries
        }
        bind<HttpClient>() with singleton { HttpClient() }
        bind<Json>() with singleton { Json { ignoreUnknownKeys = true } }
        bind<SharedPreferences>() with singleton { sharedPreferences }

        import(coreDi)
        import(viewModelsDi)
        import(currenciesDi)
        import(sourceScreenDi)
    }
}
