package com.github.felipenathananjos.autocare.ui.features.home

import com.github.felipenathananjos.autocare.model.car.EngineType
import com.github.felipenathananjos.autocare.model.car.FuelType

data class FuelConsumptionState(
    val range: Int,
    val engineType: EngineType,
    val fuel: FuelType
)