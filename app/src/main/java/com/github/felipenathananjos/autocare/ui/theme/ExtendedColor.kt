package com.rodalog.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Papéis semânticos que o Material 3 não cobre: cores por categoria de despesa.
// Cada categoria tem uma cor "on" (ícone/texto) e uma cor "container" (fundo do chip/ícone).
data class ExtendedColors(
    val categoryFuel: Color,
    val onCategoryFuel: Color,
    val categoryMaintenance: Color,
    val onCategoryMaintenance: Color,
    val categoryParts: Color,
    val onCategoryParts: Color,
    val categoryUpgrade: Color,
    val onCategoryUpgrade: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        categoryFuel = Amber10,
        onCategoryFuel = Amber40,
        categoryMaintenance = Teal10,
        onCategoryMaintenance = Teal40,
        categoryParts = Violet10,
        onCategoryParts = Violet40,
        categoryUpgrade = Coral10,
        onCategoryUpgrade = Coral40,
    )
}

// Uso em uma tela: RodalogTheme.extendedColors.categoryFuel
