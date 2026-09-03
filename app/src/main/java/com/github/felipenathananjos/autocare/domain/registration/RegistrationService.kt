package com.github.felipenathananjos.autocare.domain.registration

interface RegistrationService {
    suspend fun register(email: String, password: String): RegistrationResult
}