package com.example

import com.example.jooq.JooqExecutor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jooq.DSLContext
import org.jooq.generated.tables.City.CITY
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import kotlin.time.Duration.Companion.seconds

private val logger = LoggerFactory.getLogger(Application::class.java)

@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class])
//@SpringBootApplication
class Application {

    @Bean
    fun init(create: DSLContext, jooqExecutor: JooqExecutor) = CommandLineRunner {
        runBlocking {
            val insertQuery = create
                .insertInto(CITY, CITY.NAME)
                .values("Filip City")

            logger.info("Inserting Filip City...")
            jooqExecutor.execute(insertQuery)

            delay(5.seconds)
            logger.info("Updating Filip City...")

            val updateQuery = create
                .update(CITY)
                .set(CITY.NAME, "New Filip City")
                .where(CITY.NAME.eq("Filip City"))

            jooqExecutor.execute(updateQuery)

            delay(5.seconds)

            val query = create
                .select(CITY.NAME)
                .from(CITY)
                .where(CITY.NAME.like("%Filip City%"))
            logger.info(jooqExecutor.selectOne(query).toString())

            logger.info("Deleting Filip City")

            val deleteQuery = create
                .deleteFrom(CITY)
                .where(CITY.NAME.like("%Filip City%"))

            jooqExecutor.execute(deleteQuery)

            logger.info("!!!!!!!!!!!!!!! DONE !!!!!!!!!!!!!!!!")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

