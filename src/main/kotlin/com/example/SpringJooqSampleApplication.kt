package com.example

import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.ResultQuery
import org.jooq.generated.Tables.CITY
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@SpringBootApplication
class SpringJooqSampleApplication {

    @Bean
    fun dslContext(connectionFactory: ConnectionFactory): DSLContext {
        return DSL.using(connectionFactory)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJooqSampleApplication>(*args)
}

@RestController
class ExampleController(private val create: DSLContext) {
    private val logger = LoggerFactory.getLogger(ExampleController::class.java)

    @GetMapping("/example")
    suspend fun example(): Flow<Map<String, Any?>> {
        logger.info("Running example...")
        val fields = CITY.fields()
        val query = create
            .select(*fields)
            .from(CITY)
            .limit(10)

        return query.fetchAsFlow().map { record ->
            fields.associate { it.name to record.get(it) }
        }
    }
}

private suspend fun <T : Record> ResultQuery<T>.awaitList(): List<T> =
    Flux.from(this).asFlow().toList()

private fun <T : Record> ResultQuery<T>.fetchAsFlow(): Flow<T> = Flux.from(this).asFlow()
