package com.github.felipenathananjos.autocare.ui.components.state

data class SnackbarState(var show: Boolean = false, var message: String = "") {
    fun dismiss() {
        this.show = false
    }
}