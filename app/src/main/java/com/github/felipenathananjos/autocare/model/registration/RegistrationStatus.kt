package com.github.felipenathananjos.autocare.model.registration

enum class RegistrationStatus(val code: Int, val message: String) {
    SUCCESS(1, "Registro realizado com sucesso"), ERROR(2, "Erro ao registrar o usuário")
}