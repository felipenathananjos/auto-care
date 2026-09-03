package com.github.felipenathananjos.autocare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.felipenathananjos.autocare.ui.AppRoutes
import com.github.felipenathananjos.autocare.ui.features.home.BottomNavigation
import com.github.felipenathananjos.autocare.ui.features.home.HomeScreen
import com.github.felipenathananjos.autocare.ui.features.home.Menu
import com.github.felipenathananjos.autocare.ui.features.login.screen.LoginScreen
import com.github.felipenathananjos.autocare.ui.features.register.screen.RegisterScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.rodalog.app.ui.theme.AutoCareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            val auth = Firebase.auth
            val navController = rememberNavController()
            val startDestination = if (auth.currentUser != null) AppRoutes.Home else AppRoutes.Login

            AutoCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),snackbarHost = {
                    SnackbarHost(hostState = snackBarHostState)
                }) { innerPadding ->
                    BottomNavigation(onMenuClick = {
                        when(it) {
                            Menu.HOME -> navController.navigate(AppRoutes.Register)
                            Menu.HISTORY -> navController.navigate(AppRoutes.History)
                            Menu.VEHICLES -> navController.navigate(AppRoutes.Vehicles)
                            Menu.MAINTENANCE -> navController.navigate(AppRoutes.Maintenance)
                        }
                    }) {
                        Column(
                            Modifier.padding(
                                top = innerPadding.calculateTopPadding(), bottom = innerPadding.calculateBottomPadding()
                            )
                        ) {
                            NavHost(navController = navController, startDestination = startDestination) {
                                composable<AppRoutes.Login> {
                                    LoginScreen(snackBarHostState = snackBarHostState, goToRegistration = {
                                        navController.navigate(AppRoutes.Register)
                                    }, onLoginSuccess = {
                                        navController.navigate(AppRoutes.Home)
                                    })
                                }
                                composable<AppRoutes.Register> {
                                    RegisterScreen(snackBarHostState = snackBarHostState, onUserRegistered = {
                                        navController.popBackStack()
                                    })
                                }
                                composable<AppRoutes.NotFound> {
                                    Box(Modifier.padding(top = innerPadding.calculateTopPadding()), contentAlignment = Alignment.Center) {
                                        Text("Tela ainda não implementada")
                                    }
                                }
                                composable<AppRoutes.Home> {
                                    HomeScreen()
                                }
                                composable<AppRoutes.Vehicles> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}