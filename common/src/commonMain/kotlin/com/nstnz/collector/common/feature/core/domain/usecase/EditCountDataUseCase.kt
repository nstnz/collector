package com.nstnz.collector.common.feature.core.domain.usecase

import com.nstnz.collector.common.feature.source.data.SourcesRepository
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class EditCountDataUseCase(
    private val sourcesRepository: SourcesRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        sourceFundId: String,
        sourceId: String,
        sum: Double,
        currency: String,
        default: Boolean,
        name: String
    ) =
        withContext(dispatcher) {
            sourcesRepository.createOrUpdateSourceFund(
                SourceFundEntity(
                    id = sourceFundId,
                    sourceId = sourceId,
                    sum = sum,
                    currencyCode = currency,
                    default = default,
                    name = name
                )
            )
        }
}