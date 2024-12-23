package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.future.await
import org.jooq.Record
import org.jooq.ResultQuery
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("jdbc")
class JdbcJooqExecutor : JooqExecutor {
    override suspend fun <T : Record> select(query: ResultQuery<T>): List<T> =
        query.fetchAsync().await()

    override fun <T : Record> selectAsFlow(query: ResultQuery<T>): Flow<T> =
        query.fetchLazy().asFlow() // TODO: check this
}
