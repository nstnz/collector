package com.nstnz.collector.common.basic.di

import com.nstnz.collector.AppDatabase
import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.basic.router.Router
import com.nstnz.collector.common.basic.texts.En_Strings
import com.nstnz.collector.common.basic.texts.Strings
import com.nstnz.collector.common.feature.core.di.authDi
import com.nstnz.collector.common.feature.core.di.coreDi
import com.nstnz.collector.common.feature.core.di.viewModelsDi
import com.nstnz.collector.common.feature.currencies.di.currenciesDi
import com.nstnz.collector.common.feature.source.di.sourceScreenDi
import com.squareup.sqldelight.db.SqlDriver
import de.galdigital.preferences.SharedPreferences
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

internal val strings: Strings by SharedDI.di.instance()

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
        bind<Strings>() with singleton { En_Strings }
        bind<CoroutineDispatcher>() with singleton { Dispatchers.Default }
        bind<CoroutineScope>() with singleton {
            CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }
        bind<AppDatabaseQueries>() with singleton {
            val database = AppDatabase(databaseDriver)
            database.appDatabaseQueries
        }
        bind<HttpClient>() with singleton { HttpClient() }
        bind<Json>() with singleton { Json { ignoreUnknownKeys = true } }
        bind<SharedPreferences>() with singleton { sharedPreferences }

        import(coreDi)
        import(authDi)
        import(viewModelsDi)
        import(currenciesDi)
        import(sourceScreenDi)
    }
}
