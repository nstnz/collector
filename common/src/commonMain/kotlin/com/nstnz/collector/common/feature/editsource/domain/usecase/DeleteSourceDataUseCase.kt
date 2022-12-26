package com.nstnz.collector.common.feature.editsource.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DeleteSourceDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(sourceId: String) =
        withContext(dispatcher) {
            sourcesRepository.deleteSource(sourceId)
        }
}