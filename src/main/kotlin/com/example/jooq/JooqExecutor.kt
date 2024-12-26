package com.example.jooq

import org.jooq.Record
import org.jooq.ResultQuery

interface JooqExecutor {
    suspend fun <T : Record> select(query: ResultQuery<T>): List<T>
}
