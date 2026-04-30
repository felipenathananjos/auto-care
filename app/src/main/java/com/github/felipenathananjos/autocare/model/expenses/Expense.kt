package com.github.felipenathananjos.autocare.model.expenses

import java.time.LocalDate
import java.util.Date

data class Expense(
    var description: String,
    var value: Float,
    var type: ExpenseType,
    var date: LocalDate? = null
)