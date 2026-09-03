package com.github.felipenathananjos.autocare.ui.features.vehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.felipenathananjos.autocare.domain.vehicle.VehicleType
import com.github.felipenathananjos.autocare.ui.theme.bold
import com.github.felipenathananjos.autocare.ui.theme.dimenBigListSpacing
import com.github.felipenathananjos.autocare.ui.theme.dimenHorizontalPadding
import com.github.felipenathananjos.autocare.ui.theme.dimenTitleBottomPadding
import com.rodalog.app.ui.theme.AutoCareTheme

@Composable
fun VehiclesScreen(topPadding: Dp) {
    VehiclesContent(topPadding, VehiclesState())
}

@Composable
private fun VehiclesContent(topPadding: Dp, state: VehiclesState) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = topPadding)
            .padding(horizontal = dimenHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Veículos", style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground))
        }
        Spacer(Modifier.height(dimenTitleBottomPadding))
        LazyColumn(Modifier.fillMaxWidth()) {
            items(items = state.vehicles) { vehicle ->
                VehicleItem(vehicle)
                Spacer(Modifier.height(dimenBigListSpacing))
            }
        }
        Spacer(Modifier.height(dimenBigListSpacing))

        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .dashedBorder(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    cornerRadius = 8.dp,
                    gapLength = 4.dp,
                    dashLength = 4.dp,
                    strokeWidth = 0.8.dp
                )
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar veículo",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Adicionar Veículo",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}

fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 1.5.dp,
    cornerRadius: Dp = 16.dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 6.dp,
): Modifier = this.drawBehind {
    val stroke = Stroke(
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx()),
            phase = 0f,
        ),
    )
    drawRoundRect(
        color = color,
        size = Size(size.width, size.height),
        cornerRadius = CornerRadius(cornerRadius.toPx()),
        style = stroke,
    )
}

@Composable
private fun VehicleItem(state: VehiclesItemState) {
    val corner = 8.dp
    val color = getColorByVehicleType(state.type)
    val background = getBackgroundColorByVehicleType(state.type)
    Row(
        Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(corner))
            .border(width = 0.8.dp, shape = RoundedCornerShape(corner), color = MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = state.icon,
                tint = color,
                contentDescription = state.description,
                modifier = Modifier
                    .background(color = background, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    state.name,
                    style = MaterialTheme.typography.bodyMedium.bold().copy(color = MaterialTheme.colorScheme.onBackground)
                )
                Text(
                    state.description,
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
        Column {
            Text(
                state.mileage,
                style = MaterialTheme.typography.bodyMedium.bold().copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                "km atual",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
    }
}

@Composable
private fun getColorByVehicleType(type: VehicleType): Color {
    val colors = MaterialTheme.colorScheme
    return when (type) {
        VehicleType.CAR -> colors.onPrimaryContainer
        VehicleType.MOTORCYCLE -> colors.onSecondaryContainer
    }
}

@Composable
private fun getBackgroundColorByVehicleType(type: VehicleType): Color {
    val colors = MaterialTheme.colorScheme
    return when (type) {
        VehicleType.CAR -> colors.primaryContainer
        VehicleType.MOTORCYCLE -> colors.secondaryContainer
    }
}

data class VehiclesItemState(
    val icon: ImageVector = Icons.Default.DirectionsCar,
    val name: String = "",
    val description: String = "",
    val mileage: String = "",
    val type: VehicleType = VehicleType.CAR
)

data class VehiclesState(
    val vehicles: List<VehiclesItemState> = listOf()
)

@Composable
@Preview
private fun VehiclesContentPreview() {
    AutoCareTheme {
        VehiclesContent(
            10.dp, VehiclesState(
                listOf(
                    VehiclesItemState(
                        name = "Civic 2021",
                        description = "ABC-1D23 - Flex",
                        mileage = "42.682",
                        type = VehicleType.CAR
                    ),
                    VehiclesItemState(
                        icon = Icons.Default.SportsMotorsports,
                        name = "Honda Fan 2006",
                        description = "CDG-2F78 - Gasolina",
                        mileage = "190.123",
                        type = VehicleType.MOTORCYCLE
                    ),
                    VehiclesItemState(
                        name = "Civic 2021",
                        description = "ABC-1D23 - Flex",
                        mileage = "42.682",
                        type = VehicleType.CAR
                    ),
                )
            )
        )
    }
}