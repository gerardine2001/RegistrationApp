package com.example.registrationform.api

import com.example.registrationform.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
     suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/login/student")
    suspend fun loginStudent(@Body loginRequest: LoginRequest):Response<LoginResponse>

    @GET("/courses")
    suspend fun fetCourses(@Header("Authorization") token:String):Response<List<Courses>>

}