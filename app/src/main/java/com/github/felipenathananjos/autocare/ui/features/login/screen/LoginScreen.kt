package com.github.felipenathananjos.autocare.ui.features.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.felipenathananjos.autocare.R
import com.github.felipenathananjos.autocare.ui.components.state.SnackbarState
import com.github.felipenathananjos.autocare.ui.features.login.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), goToRegistration: () -> Unit) {

    val state by viewModel.viewState.collectAsState()
    val error by viewModel.error.collectAsState()

    ErrorSnackBar(error)
    ScreenContent(state, viewModel, goToRegistration)
}

@Composable
fun ErrorSnackBar(error: SnackbarState) {

    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (error.show) {
        LaunchedEffect(Unit) {
            scope.launch {
                snackBarState.showSnackbar(error.message)
            }
        }
    }
}

@Composable
private fun ScreenContent(state: LoginScreenState, events: LoginEvents, goToRegistration: () -> Unit) {

    val pagerState = rememberPagerState(pageCount = { 4 }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(5000)
                val position = pagerState.settledPage
                if (position == 3) pagerState.animateScrollToPage(0)
                else pagerState.animateScrollToPage(position + 1)
            }
        }
    }

    Column(
        Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        val imageModifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
        Box(Modifier.fillMaxWidth()) {
            HorizontalPager(pagerState) { page ->
                when (page) {
                    0 -> Image(
                        painter = painterResource(R.drawable.img_motorcycle_maintance_1),
                        contentDescription = "slide",
                        contentScale = ContentScale.FillBounds,
                        modifier = imageModifier
                    )

                    1 -> Image(
                        painter = painterResource(R.drawable.img_motorcycle_maintance_2),
                        contentDescription = "slide",
                        contentScale = ContentScale.FillBounds,
                        modifier = imageModifier
                    )

                    2 -> Image(
                        painter = painterResource(R.drawable.img_car_maintance_1),
                        contentDescription = "slide",
                        contentScale = ContentScale.FillBounds,
                        modifier = imageModifier
                    )

                    3 -> Image(
                        painter = painterResource(R.drawable.img_car_maintance_2),
                        contentDescription = "slide",
                        contentScale = ContentScale.FillWidth,
                        modifier = imageModifier
                    )
                }
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = state.user,
                textStyle = MaterialTheme.typography.bodySmall,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = "Usuário",
                        modifier = Modifier.size(18.dp)
                    )
                },
                onValueChange = events::onUserTextChange,
                placeholder = { Text("Usuário", style = MaterialTheme.typography.bodySmall) },
                shape = RoundedCornerShape(size = 20.dp),
                colors = OutlinedTextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.LightGray),
                modifier = Modifier
                    .height(45.dp)
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodySmall,
                value = state.password,
                onValueChange = events::onUserPasswordChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Senha",
                        modifier = Modifier.size(18.dp)
                    )
                },
                placeholder = { Text("Senha", style = MaterialTheme.typography.bodySmall) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(size = 20.dp),
                colors = OutlinedTextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.LightGray),
                modifier = Modifier
                    .height(45.dp)
            )
            Spacer(Modifier.height(12.dp))
            Button(events::onLoginButtonClick, modifier = Modifier.width(280.dp)) {
                Text("Logar")
            }
            Spacer(Modifier.height(24.dp))
            TextButton(goToRegistration) { Text("Cadastre-se") }
            Spacer(Modifier.height(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                HorizontalDivider(Modifier.width(100.dp))
                Spacer(Modifier.width(6.dp))
                Text("Ou faça Login com", style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray))
                Spacer(Modifier.width(6.dp))
                HorizontalDivider(Modifier.width(100.dp))
            }
            Spacer(Modifier.height(24.dp))
            IconButton({}) {
                Image(painter = painterResource(R.drawable.google_logo), contentDescription = "Google")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenContentPreview() {
    ScreenContent(LoginScreenState(), LoginEventsMock, {})
}

private val LoginEventsMock = object: LoginEvents {
    override fun onUserTextChange(user: String) {
        TODO("Not yet implemented")
    }

    override fun onUserPasswordChange(password: String) {
        TODO("Not yet implemented")
    }

    override fun onLoginButtonClick() {
        TODO("Not yet implemented")
    }

    override fun onRegisterButtonClick() {
        TODO("Not yet implemented")
    }

    override fun onGoogleLoginClick() {
        TODO("Not yet implemented")
    }
}