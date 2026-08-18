package com.github.felipenathananjos.autocare.ui.features.home.state

data class HomeScreenState(
    val expenses: List<ExpenseItemState> = listOf(),
    val monthSpent: String = "",
    val topCategory: String = "",
    val carList: List<CarItemState> = mutableListOf(),
    val fuelConsumption: String = ""
)
