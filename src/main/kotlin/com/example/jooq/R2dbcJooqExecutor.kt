package com.example.jooq

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.jooq.DMLQuery
import org.jooq.Record
import org.jooq.ResultQuery
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
@Profile("r2dbc")
class R2dbcJooqExecutor : JooqExecutor {
    override suspend fun <T : Record> select(query: ResultQuery<T>): List<T> =
        Flux.from(query).asFlow().toList()

    override suspend fun <T : Record> selectOne(query: ResultQuery<T>): T? =
        query.awaitFirstOrNull()

    override suspend fun <T : Record> execute(query: DMLQuery<T>) {
        query.awaitSingle()
    }
}
