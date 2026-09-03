package com.github.felipenathananjos.autocare.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.felipenathananjos.autocare.ui.components.RoundedIcon
import com.github.felipenathananjos.autocare.ui.features.home.state.VehicleItemState
import com.github.felipenathananjos.autocare.ui.features.home.state.ExpenseItemState
import com.github.felipenathananjos.autocare.ui.features.home.state.HomeScreenState
import com.github.felipenathananjos.autocare.ui.theme.bold
import com.rodalog.app.ui.theme.AutoCareTheme
import com.rodalog.app.ui.theme.LocalExtendedColors

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    ScreenContent(HomeScreenState())
}

@Composable
private fun ScreenContent(state: HomeScreenState) {
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
        CarSelector(state.carList[0].carName) { }
        Spacer(Modifier.height(32.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            FuelConsumption(state.fuelConsumption)
        }
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            MonthConsumption(state.monthSpent)
            Spacer(Modifier.width(16.dp))
            TopCategory(state.topCategory)
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "GASTOS RECENTES",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground).bold()
            )
            Text("ver tudo", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary))
        }
        RecentExpenses(state.expenses)
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
            .defaultMinSize(minWidth = 80.dp), verticalAlignment = Alignment.CenterVertically) {
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
private fun FuelConsumption(consumption: String) {
//    val unity = when (state.engineType) {
//        EngineType.ELECTRIC -> "kWh"
//        else -> {
//            when (state.fuel) {
//                FuelType.CNG -> "m3"
//                else -> "litro"
//            }
//        }
//    }
//    val consumptionText = "Média de ${state.range}km por $unity"
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "km de autonômia estimada",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer(Modifier.height(4.dp))
        Text(consumption, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
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
private fun RecentExpenses(expenses: List<ExpenseItemState>) {
    val lastIndex = if (expenses.size >= 5) 5 else expenses.size
    val firstExpenses = expenses.subList(0, lastIndex)

    if (expenses.isNotEmpty()) {
        for (expense in firstExpenses) {
            ExpenseItem(expense)
            Spacer(Modifier.height(4.dp))
        }
    } else {
        Box(Modifier.fillMaxSize().padding(top = 100.dp), contentAlignment = Alignment.TopCenter) {
            Text(
                "Você ainda não possui gastos",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Composable
private fun ExpenseItem(state: ExpenseItemState) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                ExpenseIcon(state.icon, MaterialTheme.colorScheme.primaryContainer)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        state.expense,
                        modifier = Modifier.width(180.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        state.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                state.value, style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
        HorizontalDivider(Modifier.fillMaxWidth())
    }
}

@Composable
private fun ExpenseIcon(icon: ImageVector, background: Color) {
    Box(
        Modifier
            .size(28.dp)
            .background(color = background, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = "Imagem despesa")
    }
}

//region previews

@Composable
@Preview(name = "Despesa")
private fun ExpenseItemPreview() {
    AutoCareTheme {
        ExpenseItem(
            ExpenseItemState(
                Icons.Filled.LocalGasStation,
                "Abastecimento - Posto Shell", "R$ 230,00", "28 jul. 2026"
            )
        )
    }
}

@Composable
@Preview(name = "Tela inicial")
private fun ScreenContentPreview() {
    AutoCareTheme {
        ScreenContent(
            HomeScreenState(
                expenses = listOf(
//                ExpenseItemState(
//                    expense = "Ajuste freio", value = "R$ 250,23", icon = Icons.Default.LocalGasStation, date = "Hoje, 08:14"
//                ), ExpenseItemState(
//                    expense = "Motor de arranque", value = "R$ 400,00", icon = Icons.Default.Settings, date = "Ontem"
//                ), ExpenseItemState(
//                    expense = "Balanceamento", value = "R$ 150,00", icon = Icons.Default.Settings, date = "28, jul."
//                ), ExpenseItemState(
//                    expense = "Gasolina", value = "R$ 120,00", icon = Icons.Default.LocalGasStation, date = "28, jul."
//                ), ExpenseItemState(
//                    expense = "Som Automotivo", value = "R$ 200,00", icon = Icons.Default.Star, date = "16, mai."
//                )
                ),
                monthSpent = "R$ 1.340,00",
                topCategory = "Manutenção",
                carList = listOf(
                    VehicleItemState(1, "Argo 2019"),
                    VehicleItemState(2, "CG Fan 2018"),
                ),
                fuelConsumption = "Média de 14,5 km/l"
            )
        )
    }
}

//endregion