package com.github.felipenathananjos.autocare.ui.components.state

data class DialogState(var show: Boolean = false, var title: String = "", var message: String = "") {
    fun dismiss() {
        this.show = false
    }
}