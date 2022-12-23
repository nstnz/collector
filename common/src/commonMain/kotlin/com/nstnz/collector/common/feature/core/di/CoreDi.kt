package com.nstnz.collector.common.feature.core.di

import com.nstnz.collector.common.feature.core.data.ExchangeRatesRepository
import com.nstnz.collector.common.feature.core.data.network.datasource.ExchangeRatesDataSource
import com.nstnz.collector.common.feature.core.domain.usecase.GetCurrenciesUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.GetExchangeRatesUseCase
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*

internal val coreDi = DI.Module(name = "Core") {
    bind<CoroutineDispatcher>() with provider { Dispatchers.Default }

    bind<HttpClient>() with provider { HttpClient() }
    bind<Json>() with provider {
        Json {
            ignoreUnknownKeys = true
        }
    }

    bind<ExchangeRatesDataSource>() with provider { ExchangeRatesDataSource(instance(), instance()) }

    bind<ExchangeRatesRepository>() with provider { ExchangeRatesRepository(instance()) }

    bind<GetExchangeRatesUseCase>() with provider {
        GetExchangeRatesUseCase(
            instance(),
            instance()
        )
    }
    bind<GetCurrenciesUseCase>() with provider {
        GetCurrenciesUseCase(
            instance(),
            instance()
        )
    }
}