package com.example.registrationform.api

import com.example.registrationform.models.LoginRequest
import com.example.registrationform.models.LoginResponse
import com.example.registrationform.models.RegistrationRequest
import com.example.registrationform.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest):Call<RegistrationResponse>

    @POST("/login/student")
    fun loginStudent(@Body loginRequest: LoginRequest):Call<LoginResponse>



}