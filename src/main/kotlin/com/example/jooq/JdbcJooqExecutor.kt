package com.example.jooq

import kotlinx.coroutines.future.await
import org.jooq.DMLQuery
import org.jooq.Record
import org.jooq.ResultQuery
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("jdbc")
class JdbcJooqExecutor : JooqExecutor {
    override suspend fun <T : Record> select(query: ResultQuery<T>): List<T> =
        query.fetchAsync().await()

    override suspend fun <T : Record> selectOne(query: ResultQuery<T>): T? =
        select(query).firstOrNull()

    override suspend fun <T : Record> execute(query: DMLQuery<T>) {
        query.executeAsync().await()
    }
}
