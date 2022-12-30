package com.nstnz.collector.common.feature.core.domain.scenario

import com.nstnz.collector.common.feature.core.domain.model.CurrencyDomainModel
import com.nstnz.collector.common.feature.core.domain.model.SourceDomainModel
import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourcesScenario(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val getSourceScenario: GetSourceScenario
) {

    suspend operator fun invoke(
    ): List<SourceDomainModel> = withContext(dispatcher) {
        val sources = sourcesRepository.getAllSources()
        return@withContext sources.map {
            getSourceScenario(it)
        }.sortedBy { it.name }
    }
}