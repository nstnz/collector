package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DeleteCountDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(sourceFundId: String) =
        withContext(dispatcher) {
            sourcesRepository.deleteSourceFund(sourceFundId)
        }
}