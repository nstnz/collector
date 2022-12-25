package com.nstnz.collector.common.feature.addcount.domain

import com.nstnz.collector.common.feature.currencies.data.db.model.CurrencyEntity
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import com.nstnz.collector.common.randomUUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveCountUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        currency: CurrencyEntity,
        sum: Float,
        sourceId: String
    ) = withContext(dispatcher) {
        sourcesRepository.createOrUpdateSourceFund(
            SourceFundEntity(
                id = randomUUID(),
                sourceId = sourceId,
                currencyCode = currency.code,
                sum = sum,
                default = false,
                name = ""
            )
        )
    }
}