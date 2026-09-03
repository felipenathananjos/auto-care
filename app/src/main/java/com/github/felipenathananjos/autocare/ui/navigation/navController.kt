package com.github.felipenathananjos.autocare.ui.navigation

import androidx.navigation.NavController
import com.github.felipenathananjos.autocare.ui.AppRoutes

fun NavController.safeNavigate(route: AppRoutes) {
    try {
        navigate(route)
    } catch (e: IllegalArgumentException) {
        navigate(AppRoutes.NotFound)
    }
}