package com.nstnz.collector.common.feature.source.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.datasource.SourceFundsDbDataSource
import com.nstnz.collector.common.feature.source.data.db.datasource.SourcesDbDataSource
import com.nstnz.collector.common.feature.source.presentation.SourceScreenViewModel
import org.kodein.di.*

internal val sourceScreenDi = DI.Module(name = Routes.Source.name) {
    bind<SourcesDbDataSource>() with provider {
        SourcesDbDataSource(
            instance(),
        )
    }
    bind<SourceFundsDbDataSource>() with provider {
        SourceFundsDbDataSource(
            instance(),
        )
    }
    bind<SourcesRepository>() with provider {
        SourcesRepository(
            instance(),
            instance(),
        )
    }
    bind<SourceScreenViewModel>() with multiton { sourceId: String ->
        SourceScreenViewModel(
            sourceId,
            instance(),
            instance(),
            instance(),
        )
    }
}