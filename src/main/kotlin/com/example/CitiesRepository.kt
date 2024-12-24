package com.example

interface CitiesRepository {
    suspend fun find(): List<City>
}
