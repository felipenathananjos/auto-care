package com.github.felipenathananjos.autocare.ui.features.login.screen

interface LoginEvents {
    fun onUserTextChange(user: String)
    fun onUserPasswordChange(password: String)
    fun onLoginButtonClick()
    fun onRegisterButtonClick()
    fun onGoogleLoginClick()
}