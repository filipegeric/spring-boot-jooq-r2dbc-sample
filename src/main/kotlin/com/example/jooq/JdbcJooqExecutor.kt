package com.example.jooq

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
}
