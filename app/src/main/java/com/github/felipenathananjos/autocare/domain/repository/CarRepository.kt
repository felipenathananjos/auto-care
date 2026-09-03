package com.github.felipenathananjos.autocare.domain.repository

import com.github.felipenathananjos.autocare.domain.vehicle.Vehicle
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun saveCar(expense: Vehicle)
    suspend fun fetchCars(): Flow<List<Vehicle>>
}