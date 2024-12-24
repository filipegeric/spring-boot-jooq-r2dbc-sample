package com.example

import io.r2dbc.spi.ConnectionFactory
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.util.concurrent.Executors

@Configuration
class JooqConfig {

    @Bean
    @Profile("jdbc")
    fun jdbcDslContext(configuration: org.jooq.Configuration): DSLContext =
        DSL.using(configuration.set(Executors.newVirtualThreadPerTaskExecutor()))

//    @Bean
//    @Profile("r2dbc")
//    fun r2dbcDslContext(connectionFactory: ConnectionFactory): DSLContext =
//        DSL.using(connectionFactory)
}
