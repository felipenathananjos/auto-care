package com.github.felipenathananjos.autocare.domain.registration

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class RegistrationServiceImpl @Inject constructor(private val auth: FirebaseAuth) : RegistrationService {

    override suspend fun register(email: String, password: String): RegistrationResult {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    continuation.resume(
                        RegistrationResult(
                            RegistrationStatus.ERROR,
                            task.exception?.message ?: RegistrationStatus.ERROR.message
                        )
                    )
                } else {
                    continuation.resume(RegistrationResult(RegistrationStatus.SUCCESS, RegistrationStatus.SUCCESS.message))
                }
            }
        }
    }
}