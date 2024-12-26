package com.example.jooq

import org.jooq.DMLQuery
import org.jooq.Record
import org.jooq.ResultQuery

interface JooqExecutor {
    suspend fun <T : Record> select(query: ResultQuery<T>): List<T>
    suspend fun <T : Record> selectOne(query: ResultQuery<T>): T?
    suspend fun <T : Record> execute(query: DMLQuery<T>)
}
