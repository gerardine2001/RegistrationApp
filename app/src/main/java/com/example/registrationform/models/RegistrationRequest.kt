package com.example.registrationform.models

data class RegistrationRequest(
    val name: String,
    val PhoneNumber: String,
    val email: String,
    val dateOfBirth: String,
    val nationality: String,
    val password: Any
)
