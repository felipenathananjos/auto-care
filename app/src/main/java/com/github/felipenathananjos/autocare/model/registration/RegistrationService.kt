package com.github.felipenathananjos.autocare.model.registration

interface RegistrationService {
    suspend fun register(email: String, password: String): RegistrationResult
}