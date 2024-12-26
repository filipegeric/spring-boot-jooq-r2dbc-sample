package com.example.cities

interface CitiesRepository {
    suspend fun find(): List<City>
}
