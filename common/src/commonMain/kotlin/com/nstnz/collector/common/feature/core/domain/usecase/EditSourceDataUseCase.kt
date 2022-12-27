package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class EditSourceDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(sourceId: String, name: String, currency: String) =
        withContext(dispatcher) {
            sourcesRepository.createOrUpdateSource(
                SourceEntity(
                    id = sourceId,
                    name = name,
                    currencyCode = currency,
                )
            )
        }
}