package com.example.cities

import com.example.jooq.JooqExecutor
import org.jooq.DSLContext
import org.jooq.generated.Tables.CITY
import org.jooq.impl.DSL
import org.springframework.stereotype.Component

@Component
class JooqCitiesRepository(private val create: DSLContext, private val jooqExecutor: JooqExecutor) :
    CitiesRepository {
    override suspend fun find(): List<City> {
        val query = create
            .select(*CITY.fields())
            .from(CITY)
            .orderBy(DSL.rand())
            .limit(10)

        return jooqExecutor.select(query).map {
            City(it.get(CITY.ID), it.get(CITY.NAME), it.get(CITY.POPULATION))
        }
    }
}
