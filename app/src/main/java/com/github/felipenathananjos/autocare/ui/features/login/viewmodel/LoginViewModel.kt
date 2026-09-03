package com.github.felipenathananjos.autocare.ui.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.felipenathananjos.autocare.domain.login.LoginService
import com.github.felipenathananjos.autocare.ui.components.state.SnackbarState
import com.github.felipenathananjos.autocare.ui.features.login.screen.LoginEvents
import com.github.felipenathananjos.autocare.ui.features.login.screen.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
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
class LoginViewModel @Inject constructor(
    private val service: LoginService,
) : ViewModel(), LoginEvents {

    private val _viewState = MutableStateFlow(LoginScreenState())
    val viewState: StateFlow<LoginScreenState> = _viewState.asStateFlow()

    private val _loginSuccess = MutableSharedFlow<Boolean>()
    val loginSuccess: SharedFlow<Boolean> = _loginSuccess.asSharedFlow()

    private val _error = MutableStateFlow(SnackbarState())
    val error: StateFlow<SnackbarState> = _error.asStateFlow()

    override fun onUserTextChange(user: String) = _viewState.update {
        it.copy(
            user = user
        )
    }

    override fun onUserPasswordChange(password: String) = _viewState.update {
        it.copy(
            password = password
        )
    }

    override fun onLoginButtonClick() {
        login()
    }

    private fun login() {
        _viewState.value.let { state ->
            if (!state.userFilled) {
                _error.update {
                    it.copy(
                        show = true,
                        message = "Preencha o campo usuário"
                    )
                }
                return
            }

            if (!state.passwordFilled) {
                _error.update {
                    it.copy(
                        show = true,
                        message = "Preencha a senha"
                    )
                }
                return
            }

            service.login(state.user, state.password).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    viewModelScope.launch {
                        _loginSuccess.emit(true)
                    }
                } else {
                    _error.update {
                        it.copy(
                            show = true,
                            message = result.exception?.message
                                ?: "Ocorreu um erro ao realizar o Login"
                        )
                    }
                }
            }
        }
    }

    override fun onRegisterButtonClick() {

    }

    override fun onGoogleLoginClick() {

    }
}