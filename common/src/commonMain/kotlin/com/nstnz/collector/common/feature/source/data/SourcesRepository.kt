package com.nstnz.collector.common.feature.source.data

import com.nstnz.collector.common.feature.source.data.db.datasource.SourceFundsDbDataSource
import com.nstnz.collector.common.feature.source.data.db.datasource.SourcesDbDataSource
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity
import com.nstnz.collector.common.feature.source.domain.model.SourceFundModel
import com.nstnz.collector.common.feature.source.domain.model.SourceModel

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

    suspend fun deleteSourceFund(sourceFundId: String) =
        sourceFundsDbDataSource.deleteSourceFund(
            sourceFundId = sourceFundId
        )

    suspend fun getSource(sourceId: String): SourceModel? =
        sourcesDbDataSource.getSource(sourceId)?.let {
            val results = sourceFundsDbDataSource.getAllSourceFunds(it.id)
            SourceModel(
                id = it.id,
                name = it.name,
                currencyCode = it.currencyCode,
                funds = results.map { fund ->
                    SourceFundModel(
                        id = fund.id,
                        sourceId = fund.sourceId,
                        currencyCode = fund.currencyCode,
                        sum = fund.sum,
                        isDefault = fund.default,
                        name = fund.name
                    )
                }
            )
        }

    suspend fun getAllSources(): List<SourceModel> {
        return sourcesDbDataSource.getAllSources().map {
            val results = sourceFundsDbDataSource.getAllSourceFunds(it.id)
            SourceModel(
                id = it.id,
                name = it.name,
                currencyCode = it.currencyCode,
                funds = results.map { fund ->
                    SourceFundModel(
                        id = fund.id,
                        sourceId = fund.sourceId,
                        currencyCode = fund.currencyCode,
                        sum = fund.sum,
                        isDefault = fund.default,
                        name = fund.name
                    )
                }
            )
        }
    }
}