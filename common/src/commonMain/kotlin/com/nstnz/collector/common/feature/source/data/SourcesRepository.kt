package com.nstnz.collector.common.feature.source.data

import com.nstnz.collector.common.feature.source.data.db.datasource.SourceFundsDbDataSource
import com.nstnz.collector.common.feature.source.data.db.datasource.SourcesDbDataSource
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity

internal class SourcesRepository(
    private val sourcesDbDataSource: SourcesDbDataSource,
    private val sourceFundsDbDataSource: SourceFundsDbDataSource,
) {

    suspend fun createOrUpdateSource(source: SourceEntity) =
        sourcesDbDataSource.createSource(source.id, source.name, source.currencyCode)

    suspend fun deleteSource(sourceId: String) =
        sourcesDbDataSource.deleteSource(sourceId)

    suspend fun createOrUpdateSourceFund(sourceFund: SourceFundEntity) =
        sourceFundsDbDataSource.updateSourceFund(
            sourceFund = sourceFund
        )

    suspend fun getSourceFund(sourceFundId: String) =
        sourceFundsDbDataSource.getSourceFund(
            sourceFundId = sourceFundId
        )

    suspend fun getAllSourceFunds(sourceId: String) =
        sourceFundsDbDataSource.getAllSourceFunds(
            sourceId = sourceId
        )

    suspend fun deleteSourceFund(sourceFundId: String) =
        sourceFundsDbDataSource.deleteSourceFund(
            sourceFundId = sourceFundId
        )

    suspend fun getSource(sourceId: String): SourceEntity? =
        sourcesDbDataSource.getSource(sourceId)

    suspend fun getAllSources(): List<SourceEntity> {
        return sourcesDbDataSource.getAllSources()
    }
}