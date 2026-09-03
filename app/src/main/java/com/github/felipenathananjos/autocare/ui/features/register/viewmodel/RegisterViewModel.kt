package com.github.felipenathananjos.autocare.ui.features.register.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.felipenathananjos.autocare.domain.registration.RegistrationService
import com.github.felipenathananjos.autocare.domain.registration.RegistrationStatus
import com.github.felipenathananjos.autocare.ui.components.state.DialogState
import com.github.felipenathananjos.autocare.ui.features.register.screen.RegisterEvents
import com.github.felipenathananjos.autocare.ui.features.register.screen.RegisterScreenState
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction
import com.google.android.recaptcha.RecaptchaClient
import com.google.android.recaptcha.RecaptchaException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val service: RegistrationService,
    @ApplicationContext private val context: Context
) : ViewModel(), RegisterEvents {
    private val _screenState = MutableStateFlow(RegisterScreenState())
    val screenState: StateFlow<RegisterScreenState> = _screenState.asStateFlow()

    private val registration: RegisterScreenState
        get() = _screenState.value

    private val _registrationSuccessful = MutableSharedFlow<Boolean>()
    val registrationSuccessful: SharedFlow<Boolean> = _registrationSuccessful.asSharedFlow()

    private val passwordsMatches: Boolean
        get() = registration.password == registration.confirmPassword
    private val _error = MutableStateFlow(DialogState())
    val error: StateFlow<DialogState> = _error.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _error.update {
            it.copy(
                show = true,
                message = throwable.message.toString()
            )
        }
    }

    private lateinit var recaptchaClient: RecaptchaClient

    override fun onEmailTextChange(email: String) = _screenState.update {
        it.copy(
            email = email
        )
    }

    override fun onPasswordTextChange(password: String) = _screenState.update {
        it.copy(
            password = password
        )
    }

    override fun onConfirmPasswordTextChange(confirmPassword: String) = _screenState.update {
        it.copy(
            confirmPassword = confirmPassword
        )
    }

    override fun onRegisterButtonClick() {

        viewModelScope.launch(exceptionHandler) {

            if (registration.email.isNotEmpty()) {
                if (passwordsMatches) {
                    val result = service.register(registration.email, registration.confirmPassword)
                    if (result.status.code == RegistrationStatus.SUCCESS.code) {
                        Log.i("REGISTRATION", RegistrationStatus.SUCCESS.message)
                        _registrationSuccessful.emit(true)
                    } else {
                        throw Exception(result.message)
                    }
                } else {
                    throw Exception("Senhas não conferem")
                }
            } else {
                throw Exception("E-mail é obrigatório")
            }
        }
    }

    override fun closeErrorDialog() {
        _error.update {
            DialogState()
        }
    }

    private fun executeLoginAction() {
        viewModelScope.launch(exceptionHandler) {
            recaptchaClient
                .execute(RecaptchaAction.SIGNUP)
                .onSuccess { token ->

                }
                .onFailure { exception ->

                }
        }
    }

    init {
        initializeRecaptchaClient()
    }

    private fun initializeRecaptchaClient() {
        viewModelScope.launch {
            try {
                recaptchaClient = Recaptcha.fetchClient(context as Application, "6Lfv6c0rAAAAAKX_FpAiGqIsIuM0Q5KJ0HnNcA2e")
                Log.i("recaptcha", "sucesso adquirir cliente recaptcha")
            } catch (e: RecaptchaException) {
                Log.e("recaptcha", e.message, e)
            }
        }
    }
}