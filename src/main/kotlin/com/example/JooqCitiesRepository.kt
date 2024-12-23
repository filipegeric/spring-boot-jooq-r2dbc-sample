package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jooq.DSLContext
import org.jooq.generated.Tables.CITY
import org.springframework.stereotype.Component

@Component
class JooqCitiesRepository(private val create: DSLContext, private val jooqExecutor: JooqExecutor) :
    CitiesRepository {
    override fun find(): Flow<City> {
        val query = create
            .select(*CITY.fields())
            .from(CITY)
            .orderBy(CITY.ID.desc())
            .limit(10)

        return jooqExecutor.selectAsFlow(query).map {
            City(it.get(CITY.ID), it.get(CITY.NAME), it.get(CITY.POPULATION))
        }
    }
}
