package com.nstnz.collector.common.feature.source.data.db.datasource

import com.nstnz.collector.AppDatabaseQueries
import com.nstnz.collector.common.feature.source.data.db.model.SourceEntity

internal class SourcesDbDataSource(
    private val queries: AppDatabaseQueries
) {
    fun getAllSources(): List<SourceEntity> {
        return queries.getAllSources(::mapSource).executeAsList()
    }

    fun getSource(sourceId: String): SourceEntity? {
        return queries.getSource(sourceId, ::mapSource).executeAsOneOrNull()
    }

    fun createSource(id: String, name: String, currencyCode: String) {
        queries.insertSource(
            id = id,
            name = name,
            currencyCode = currencyCode,
        )
    }

    private fun mapSource(
        id: String,
        name: String,
        currencyCode: String,
    ): SourceEntity {
        return SourceEntity(
            id = id,
            name = name,
            currencyCode = currencyCode
        )
    }
}