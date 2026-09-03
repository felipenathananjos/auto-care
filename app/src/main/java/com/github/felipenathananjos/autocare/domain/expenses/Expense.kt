package com.github.felipenathananjos.autocare.domain.expenses

import com.github.felipenathananjos.autocare.domain.vehicle.Vehicle
import java.time.LocalDate

data class Expense(
    var description: String,
    var value: Float,
    var type: ExpenseType,
    var date: LocalDate? = null,
    var vehicle: Vehicle
)