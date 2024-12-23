package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.jooq.Record
import org.jooq.ResultQuery
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant
import kotlin.time.toKotlinDuration

@RestController
class ExampleController(private val repository: CitiesRepository) {
    private val logger = LoggerFactory.getLogger(ExampleController::class.java)

    @GetMapping("/example")
    fun example(): Flow<City> {
        logger.info("Running example...")
        val start = Instant.now()
        return repository.find().onCompletion {
            val end = Instant.now()
            logger.info("That took: ${Duration.between(start, end).toKotlinDuration()}")
        }
    }
}

private suspend fun <T : Record> ResultQuery<T>.awaitList(): List<T> = fetchAsFlow().toList()
private fun <T : Record> ResultQuery<T>.fetchAsFlow(): Flow<T> = Flux.from(this).asFlow()
