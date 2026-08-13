package com.github.felipenathananjos.autocare.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.felipenathananjos.autocare.model.car.EngineType
import com.github.felipenathananjos.autocare.model.car.FuelType
import com.github.felipenathananjos.autocare.model.expenses.Expense
import com.github.felipenathananjos.autocare.model.expenses.ExpenseType
import com.github.felipenathananjos.autocare.ui.components.RoundedIcon
import com.github.felipenathananjos.autocare.ui.theme.bold
import com.rodalog.app.ui.theme.AutoCareTheme
import com.rodalog.app.ui.theme.LocalExtendedColors
import java.time.LocalDate

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
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoundedIcon(Icons.Filled.Person)
                Spacer(Modifier.width(6.dp))
                Text("Olá,\nFulano", style = MaterialTheme.typography.titleSmall)
            }
            RoundedIcon(Icons.Filled.Notifications)
        }
        Spacer(Modifier.height(8.dp))
        CarSelector("Argo 2019") { }
        Spacer(Modifier.height(32.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            FuelConsumption(FuelConsumptionState(15, EngineType.COMBUSTION, FuelType.GASOLINE))
        }
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            MonthConsumption("1.340")
            Spacer(Modifier.width(16.dp))
            TopCategory("Manutenção")
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "GASTOS RECENTES",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground).bold()
            )
            Text("ver tudo", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary))
        }
    }
}

@Composable
fun CarSelector(selectedCar: String, onItemClick: () -> Unit) {
    Row(
        Modifier
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(19.dp))
            .clickable {
                onItemClick()
            }
            .padding(4.dp)
            .defaultMinSize(minWidth = 80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.DirectionsCar,
            contentDescription = "Selecionar carro",
            Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(4.dp))
        Text(selectedCar, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(4.dp))
        Icon(
            Icons.Filled.KeyboardArrowDown,
            contentDescription = "Listar carros",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun FuelConsumption(state: FuelConsumptionState) {
    val unity = when (state.engineType) {
        EngineType.ELECTRIC -> "kWh"
        else -> {
            when (state.fuel) {
                FuelType.CNG -> "m3"
                else -> "litro"
            }
        }
    }
    val consumptionText = "Média de ${state.range}km por $unity"
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "km de autonômia estimada",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer(Modifier.height(4.dp))
        Text(consumptionText, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
    }
}

@Composable
private fun MonthConsumption(value: String) {
    val colors = LocalExtendedColors.current
    val valueStyle = MaterialTheme.typography.titleLarge.copy(color = colors.onCategoryFuel)
    Column(
        Modifier
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(0.4f)
            .height(130.dp)
            .padding(8.dp)
    ) {
        Text("GASTO NO\nMÊS", style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface))
        Spacer(Modifier.height(8.dp))
        Text("R$", style = valueStyle)
        Text(value, style = valueStyle)
    }
}

@Composable
private fun TopCategory(category: String) {
    val colors = LocalExtendedColors.current
    val categoryStyle =
        MaterialTheme.typography.titleLarge.copy(color = colors.onCategoryMaintenance, fontWeight = FontWeight.Bold)
    Column(
        Modifier
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .height(130.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("MAIOR GASTO", style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface))
        Spacer(Modifier.height(8.dp))
        Text(category, style = categoryStyle)
    }
}

@Composable
private fun RecentExpenses() {
    
}

@Composable
@Preview
private fun ScreenContentPreview() {
    AutoCareTheme {
        ScreenContent(
            listOf(
                Expense(
                    description = "Ajuste freio",
                    value = 350.75f,
                    type = ExpenseType.MAINTENANCE,
                    date = LocalDate.now()
                ),
                Expense(
                    description = "Motor de arranque",
                    value = 180.50f,
                    type = ExpenseType.PARTS,
                    date = LocalDate.now()
                ),
                Expense(
                    description = "Balanceamento",
                    value = 45.90f,
                    type = ExpenseType.MAINTENANCE,
                    date = LocalDate.now()
                ),
                Expense(
                    description = "Gasolina",
                    value = 150.00f,
                    type = ExpenseType.FUEL,
                    date = LocalDate.now()
                )
            )
        )
    }
}