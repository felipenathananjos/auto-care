package com.rodalog.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColors = darkColorScheme(
    primary = Amber40,
    onPrimary = Amber90,
    primaryContainer = Amber10,
    onPrimaryContainer = Amber40,

    secondary = Teal40,
    onSecondary = Teal10,
    secondaryContainer = Teal10,
    onSecondaryContainer = Teal40,

    tertiary = Violet40,
    onTertiary = Violet10,

    error = Red40,
    onError = Neutral99,

    background = Neutral10,
    onBackground = Neutral99,

    surface = Neutral15,
    onSurface = Neutral99,
    surfaceVariant = Neutral20,
    onSurfaceVariant = Neutral60,

    outline = Neutral30,
    outlineVariant = Neutral25,
)

private val ExtendedDarkColors = ExtendedColors(
    categoryFuel = Amber10,
    onCategoryFuel = Amber40,
    categoryMaintenance = Teal10,
    onCategoryMaintenance = Teal40,
    categoryParts = Violet10,
    onCategoryParts = Violet40,
    categoryUpgrade = Coral10,
    onCategoryUpgrade = Coral40,
)

@Composable
fun AutoCareTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = DarkColors
    val extendedColors = ExtendedDarkColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

// Acesso curto: RodalogTheme.extendedColors.categoryFuel
object AutoCareTheme {
    val extendedColors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
