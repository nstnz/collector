package com.nstnz.collector.common.feature.addsource.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.randomUUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class SaveSourceDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(name: String, currency: String) = withContext(dispatcher) {
        val sourceId = randomUUID()
        sourcesRepository.createOrUpdateSource(
            SourceEntity(
                id = sourceId,
                name = name,
                currencyCode = currency,
            )
        )
        sourceId
    }
}