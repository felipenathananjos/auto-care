package com.github.felipenathananjos.autocare.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.felipenathananjos.autocare.model.expenses.Expense
import com.github.felipenathananjos.autocare.model.expenses.ExpenseType

@Composable
fun HomeScreen() {
    ScreenContent(listOf())
}

@Composable
private fun ScreenContent(expenseList: List<Expense>) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(28.dp)
                        .background(
                            shape = RoundedCornerShape(corner = CornerSize(28.dp)),
                            color = MaterialTheme.colorScheme.secondary
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Usuário")
                }
                Spacer(Modifier.width(6.dp))
                Text("Olá,\nFulano", style = MaterialTheme.typography.titleSmall)
            }
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "Configurações", Modifier.size(24.dp))
        }
        Spacer(Modifier.height(24.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray, shape = RoundedCornerShape(corner = CornerSize(8.dp)))
                .padding(8.dp)
                .height(250.dp)
        ) {
            Text("Últimos gastos", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
@Preview
private fun ScreenContentPreview() {
    ScreenContent(listOf(
        Expense(
            description = "Ajuste freio",
            value = 350.75f,
            type = ExpenseType.MAINTENANCE
        ),
        Expense(
            description = "Motor de arranque",
            value = 180.50f,
            type = ExpenseType.PARTS
        ),
        Expense(
            description = "Balanceamento",
            value = 45.90f,
            type = ExpenseType.MAINTENANCE
        ),
        Expense(
            description = "Gasolina",
            value = 150.00f,
            type = ExpenseType.FUEL
        )
    ))
}