package com.example.cities

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.Instant
import kotlin.time.toKotlinDuration

@RestController
class CitiesController(private val repository: CitiesRepository) {
    private val logger = LoggerFactory.getLogger(CitiesController::class.java)

    @GetMapping("/cities")
    suspend fun findCities(): List<City> {
        logger.info("Finding cities...")
        val start = Instant.now()
        return repository.find().also {
            val end = Instant.now()
            logger.info("That took: ${Duration.between(start, end).toKotlinDuration()}")
        }
    }
}
