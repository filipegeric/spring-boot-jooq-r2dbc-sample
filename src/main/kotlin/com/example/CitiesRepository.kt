package com.example

import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun find(): Flow<City>
}
