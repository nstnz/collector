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

    suspend fun createSource(source: SourceEntity) =
        sourcesDbDataSource.createSource(source.id, source.name)

    suspend fun createOrUpdateSourceFund(sourceFund: SourceFundEntity) =
        sourceFundsDbDataSource.updateSourceFund(
            sourceFund = sourceFund
        )

    suspend fun getSource(sourceId: String): SourceModel? =
        sourcesDbDataSource.getSource(sourceId)?.let {
            val results = sourceFundsDbDataSource.getAllSourceFunds(it.id)
            SourceModel(
                id = it.id,
                name = it.name,
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