package com.example

import kotlinx.coroutines.flow.Flow
import org.jooq.Record
import org.jooq.ResultQuery

interface JooqExecutor {
    suspend fun <T : Record> select(query: ResultQuery<T>): List<T>
    fun <T : Record> selectAsFlow(query: ResultQuery<T>): Flow<T>
}
