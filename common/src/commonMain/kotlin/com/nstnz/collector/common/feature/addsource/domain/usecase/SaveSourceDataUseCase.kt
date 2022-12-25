package com.nstnz.collector.common.feature.addsource.domain.usecase

import com.nstnz.collector.common.feature.currencies.data.prefs.CurrenciesPrefs
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import com.nstnz.collector.common.feature.source.domain.model.SourceModel
import com.nstnz.collector.common.randomUUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveSourceDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val prefs: CurrenciesPrefs,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(name: String, currency: String) = withContext(dispatcher) {
        val sourceId = randomUUID()
        sourcesRepository.createSource(
            SourceEntity(
                id = sourceId,
                name = name
            )
        )
        sourcesRepository.createOrUpdateSourceFund(
            SourceFundEntity(
                id = randomUUID(),
                sourceId = sourceId,
                currencyCode = currency,
                sum = 0f,
                default = true,
                name = "TODO"
            )
        )
        sourceId
    }
}