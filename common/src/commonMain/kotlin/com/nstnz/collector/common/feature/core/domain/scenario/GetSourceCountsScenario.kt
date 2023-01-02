package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.SourceCountDomainModel
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceCountsScenario(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getSourceCountScenario: GetSourceCountScenario,
) {

    suspend operator fun invoke(
        sourceId: String,
    ): List<SourceCountDomainModel> = withContext(dispatcher) {
        val sourceCurrencyCode = sourcesRepository.getSource(sourceId)?.currencyCode
        val counts = sourcesRepository.getAllSourceFunds(sourceId)
        return@withContext counts.map {
            getSourceCountScenario(it, sourceCurrencyCode)
        }.sortedBy { it.id.toLongOrNull() }
    }
}