package com.nstnz.collector.common.feature.editcount.di

import com.nstnz.collector.common.basic.router.Routes
import com.nstnz.collector.common.feature.editcount.domain.usecase.DeleteCountDataUseCase
import com.nstnz.collector.common.feature.editcount.domain.usecase.EditCountDataUseCase
import com.nstnz.collector.common.feature.editcount.domain.usecase.GetSourceFundDataUseCase
import com.nstnz.collector.common.feature.editcount.presentation.EditCountScreenViewModel
import org.kodein.di.*

internal val editCountScreenDi = DI.Module(name = Routes.EditCount.name) {
    bind<EditCountScreenViewModel>() with multiton { sourceFundId: String ->
        EditCountScreenViewModel(
            sourceFundId,
            instance(),
            instance(),
            instance(),
            instance(),
            instance(),
        )
    }
    bind<DeleteCountDataUseCase>() with provider {
        DeleteCountDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<GetSourceFundDataUseCase>() with provider {
        GetSourceFundDataUseCase(
            instance(),
            instance(),
        )
    }
    bind<EditCountDataUseCase>() with provider {
        EditCountDataUseCase(
            instance(),
            instance(),
        )
    }
}