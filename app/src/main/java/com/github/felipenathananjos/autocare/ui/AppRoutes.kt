package com.github.felipenathananjos.autocare.ui

import kotlinx.serialization.Serializable

sealed interface AppRoutes {

    @Serializable
    data object Login: AppRoutes

    @Serializable
    data object Register: AppRoutes

    @Serializable
    data object Home: AppRoutes
}