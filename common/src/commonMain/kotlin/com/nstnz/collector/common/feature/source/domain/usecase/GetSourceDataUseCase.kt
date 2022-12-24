package com.nstnz.collector.common.feature.source.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetSourceDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(sourceId: String) = withContext(dispatcher) {
        sourcesRepository.getSource(sourceId)
    }
}