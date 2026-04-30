package com.github.felipenathananjos.autocare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.felipenathananjos.autocare.ui.AppRoutes
import com.github.felipenathananjos.autocare.ui.features.home.HomeScreen
import com.github.felipenathananjos.autocare.ui.features.login.screen.LoginScreen
import com.github.felipenathananjos.autocare.ui.features.register.screen.RegisterScreen
import com.github.felipenathananjos.autocare.ui.theme.AutoCareTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val auth = Firebase.auth
            val navController = rememberNavController()
            val startDestination = if (auth.currentUser != null) AppRoutes.Home else AppRoutes.Login

            AutoCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }) { innerPadding ->
                    Column(
                        Modifier.padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding()
                        )
                    ) {
                        NavHost(navController = navController, startDestination = startDestination) {
                            composable<AppRoutes.Login> {
                                LoginScreen(goToRegistration = {
                                    navController.navigate(AppRoutes.Register)
                                })
                            }
                            composable<AppRoutes.Register> {
                                RegisterScreen(snackbarHostState = snackbarHostState)
                            }
                            composable<AppRoutes.Home> {
                                HomeScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutoCareTheme {
        Greeting("Android")
    }
}