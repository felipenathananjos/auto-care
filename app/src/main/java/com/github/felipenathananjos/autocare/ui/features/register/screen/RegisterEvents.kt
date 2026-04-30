package com.github.felipenathananjos.autocare.ui.features.register.screen

interface RegisterEvents {
    fun onEmailTextChange(email: String)
    fun onPasswordTextChange(password: String)
    fun onConfirmPasswordTextChange(confirmPassword: String)
    fun onRegisterButtonClick()
    fun closeErrorDialog()
}