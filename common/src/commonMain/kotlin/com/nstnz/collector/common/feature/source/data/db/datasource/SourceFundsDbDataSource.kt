package com.nstnz.collector.common.feature.source.data.db.datasource

import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity
import com.nstnz.collector.common.feature.source.data.db.model.SourceFundEntity

internal class SourceFundsDbDataSource(
    private val queries: AppDatabaseQueries
) {

    fun getAllSourceFunds(sourceId: String): List<SourceFundEntity> {
        return queries.getAllSourceFunds(sourceId, ::mapSourceFund).executeAsList()
    }

    fun updateSourceFund(sourceFund: SourceFundEntity) {
        queries.insertSourceFund(
            id = sourceFund.id,
            sourceId = sourceFund.sourceId,
            sum = sourceFund.sum.toDouble(),
            currencyCode = sourceFund.currencyCode,
            isDefault = sourceFund.default,
            name = sourceFund.name
        )
    }

    fun getSourceFund(sourceFundId: String) =
        queries.getSourceFund(
            sourceFundId, ::mapSourceFund
        ).executeAsOneOrNull()

    fun deleteSourceFund(sourceFundId: String) {
        queries.deleteSourceFund(
            id = sourceFundId
        )
    }

    private fun mapSourceFund(
        id: String,
        sourceId: String,
        currencyCode: String,
        sum: Double,
        isDefault: Boolean,
        name: String,
    ): SourceFundEntity {
        return SourceFundEntity(
            id = id,
            sourceId = sourceId,
            currencyCode = currencyCode,
            sum = sum.toFloat(),
            default = isDefault,
            name = name
        )
    }
}