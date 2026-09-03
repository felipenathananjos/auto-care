package com.github.felipenathananjos.autocare.domain.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface LoginService {
    fun login(user: String, password: String): Task<AuthResult>
}