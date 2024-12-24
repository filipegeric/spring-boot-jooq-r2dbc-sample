package com.example

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.Instant
import kotlin.time.toKotlinDuration

@RestController
class ExampleController(private val repository: CitiesRepository) {
    private val logger = LoggerFactory.getLogger(ExampleController::class.java)

    @GetMapping("/example")
    suspend fun example(): List<City> {
        logger.info("Running example...")
        val start = Instant.now()
        return repository.find().also {
            val end = Instant.now()
            logger.info("That took: ${Duration.between(start, end).toKotlinDuration()}")
        }
    }
}
