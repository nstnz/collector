package com.nstnz.collector.common.feature.listsource.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.listsource.presentation.ListSourceScreenViewModel
import com.nstnz.collector.common.feature.listsource.presentation.ListSourceViewModelParams
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.multiton

internal val listSourceScreenDi = DI.Module(name = Routes.ListSource.name) {
    bind<ListSourceScreenViewModel>() with multiton { params: ListSourceViewModelParams ->
        ListSourceScreenViewModel(
            params.sourceId,
            params.sourceFundId,
            instance(),
            instance(),
        )
    }
}