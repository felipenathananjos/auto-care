package com.github.felipenathananjos.autocare.ui.features.register.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.felipenathananjos.autocare.ui.components.SimpleDialog
import com.github.felipenathananjos.autocare.ui.features.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel(), snackbarHostState: SnackbarHostState) {

    val state by viewModel.screenState.collectAsState()
    val error by viewModel.error.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.registrationSuccessful.collect {
            if (it) {
                snackbarHostState.showSnackbar("Registro realizado com sucesso")
            }
        }
    }

    ScreenContent(state, viewModel)
    SimpleDialog(error, viewModel::closeErrorDialog)
}

@Composable
private fun ScreenContent(state: RegisterScreenState, events: RegisterEvents) {

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(80.dp))
        OutlinedTextField(
            value = state.email,
            textStyle = MaterialTheme.typography.bodySmall,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email",
                    modifier = Modifier.size(18.dp)
                )
            },
            onValueChange = events::onEmailTextChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text("Email", style = MaterialTheme.typography.bodySmall) },
            shape = RoundedCornerShape(size = 20.dp),
            colors = OutlinedTextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.LightGray),
            modifier = Modifier
                .height(45.dp)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = state.password,
            textStyle = MaterialTheme.typography.bodySmall,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Senha",
                    modifier = Modifier.size(18.dp)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = events::onPasswordTextChange,
            placeholder = { Text("Senha", style = MaterialTheme.typography.bodySmall) },
            shape = RoundedCornerShape(size = 20.dp),
            colors = OutlinedTextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.LightGray),
            modifier = Modifier
                .height(45.dp)
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = state.confirmPassword,
            textStyle = MaterialTheme.typography.bodySmall,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Confirmar senha",
                    modifier = Modifier.size(18.dp)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = events::onConfirmPasswordTextChange,
            placeholder = { Text("Confirmar senha", style = MaterialTheme.typography.bodySmall) },
            shape = RoundedCornerShape(size = 20.dp),
            colors = OutlinedTextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.LightGray),
            modifier = Modifier
                .height(45.dp)
        )
        Spacer(Modifier.height(24.dp))
        Button(onClick = events::onRegisterButtonClick) {
            Text("Cadastrar")
        }
    }
}

@Composable
@Preview
private fun ScreenContentPreview() {
    ScreenContent(RegisterScreenState(
        "", "", ""
    ), ScreenEventsMock)
}

private val ScreenEventsMock = object: RegisterEvents {
    override fun onEmailTextChange(email: String) {
        TODO("Not yet implemented")
    }

    override fun onPasswordTextChange(password: String) {
        TODO("Not yet implemented")
    }

    override fun onConfirmPasswordTextChange(confirmPassword: String) {
        TODO("Not yet implemented")
    }

    override fun onRegisterButtonClick() {
        TODO("Not yet implemented")
    }

    override fun closeErrorDialog() {
        TODO("Not yet implemented")
    }
}