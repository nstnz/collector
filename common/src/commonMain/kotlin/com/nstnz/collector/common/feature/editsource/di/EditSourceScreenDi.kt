package com.nstnz.collector.common.feature.editsource.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.editsource.domain.usecase.DeleteSourceDataUseCase
import com.nstnz.collector.common.feature.editsource.domain.usecase.EditSourceDataUseCase
import com.nstnz.collector.common.feature.editsource.presentation.EditSourceScreenViewModel
import org.kodein.di.*

internal val editSourceScreenDi = DI.Module(name = Routes.EditSource.name) {
    bind<EditSourceScreenViewModel>() with multiton { sourceId: String ->
        EditSourceScreenViewModel(
            sourceId,
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<DeleteSourceDataUseCase>() with provider {
        DeleteSourceDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<EditSourceDataUseCase>() with provider {
        EditSourceDataUseCase(
            instance(),
            instance(),
        )
    }
}