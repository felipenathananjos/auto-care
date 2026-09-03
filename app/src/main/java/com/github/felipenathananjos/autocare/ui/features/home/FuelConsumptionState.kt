package com.github.felipenathananjos.autocare.ui.features.home

import com.github.felipenathananjos.autocare.domain.vehicle.EngineType
import com.github.felipenathananjos.autocare.domain.vehicle.FuelType

data class FuelConsumptionState(
    val range: Int,
    val engineType: EngineType,
    val fuel: FuelType
)