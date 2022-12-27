package com.nstnz.collector.common.feature.editcount.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.core.domain.usecase.DeleteCountDataUseCase
import com.nstnz.collector.common.feature.core.domain.usecase.EditCountDataUseCase
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreenViewModel
import org.kodein.di.*

internal val editCountScreenDi = DI.Module(name = Routes.EditCount.name) {
    bind<EditCountScreenViewModel>() with multiton { sourceFundId: String ->
        EditCountScreenViewModel(
            sourceFundId,
            instance(),
            instance(),
            instance(),
        )
    }
}