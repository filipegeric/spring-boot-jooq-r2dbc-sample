package com.example

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class JooqConfig {

    @Bean
    @Profile("jdbc")
    fun jdbcDslContext(configuration: org.jooq.Configuration): DSLContext =
        DSL.using(configuration)

//    @Bean
//    @Profile("r2dbc")
//    fun r2dbcDslContext(connectionFactory: ConnectionFactory): DSLContext =
//        DSL.using(connectionFactory)
}
