package com.github.felipenathananjos.autocare.ui.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Menu(val icon: ImageVector, val label: String) {
    HOME(Icons.Outlined.Home, "Início"),
    HISTORY(Icons.AutoMirrored.Filled.ReceiptLong, "Histórico"),
    VEHICLES(Icons.Outlined.DirectionsCar, "Veículos"),
    MAINTENANCE(Icons.Outlined.EventAvailable, "Manutenção")
}