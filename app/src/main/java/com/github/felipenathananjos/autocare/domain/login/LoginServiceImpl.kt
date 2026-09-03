package com.github.felipenathananjos.autocare.domain.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(private val auth: FirebaseAuth): LoginService {
    override fun login(user: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(user, password)
    }
}