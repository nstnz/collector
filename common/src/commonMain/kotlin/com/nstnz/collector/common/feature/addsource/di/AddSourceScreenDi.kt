package com.nstnz.collector.common.feature.addsource.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.addsource.presentation.AddSourceScreenViewModel
import com.nstnz.collector.common.feature.main.domain.scenario.GetSourcesScenario
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.datasource.SourceFundsDbDataSource
import com.nstnz.collector.common.feature.source.data.db.datasource.SourcesDbDataSource
import com.nstnz.collector.common.feature.source.domain.scenario.GetSourceScenario
import com.nstnz.collector.common.feature.source.presentation.SourceScreenViewModel
import org.kodein.di.*

internal val addSourceScreenDi = DI.Module(name = Routes.AddSource.name) {

    bind<AddSourceScreenViewModel>() with provider {
        AddSourceScreenViewModel(
            instance(),
        )
    }
}