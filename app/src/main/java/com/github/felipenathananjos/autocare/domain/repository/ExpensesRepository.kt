package com.github.felipenathananjos.autocare.domain.repository

import com.github.felipenathananjos.autocare.domain.expenses.Expense
import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {
    suspend fun saveExpense(expense: Expense)
    suspend fun fetchExpenses(): Flow<List<Expense>>
}