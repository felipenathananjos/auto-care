package com.github.felipenathananjos.autocare.ui.features.login.screen

data class LoginScreenState(
    var user: String = "",
    var password: String = ""
) {
    val userFilled: Boolean
        get() = user.isNotEmpty()

    val passwordFilled: Boolean
        get() = password.isNotEmpty()
}