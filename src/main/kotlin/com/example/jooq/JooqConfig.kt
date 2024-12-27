package com.example.jooq

import io.r2dbc.spi.ConnectionFactory
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig {
    @Bean
    fun r2dbcDslContext(connectionFactory: ConnectionFactory): DSLContext =
        DSL.using(connectionFactory)
}
