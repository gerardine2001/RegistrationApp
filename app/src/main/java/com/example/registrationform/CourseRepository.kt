package com.example.registrationform

import com.example.registrationform.api.ApiClient
import com.example.registrationform.api.ApiInterface
import com.example.registrationform.models.Courses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CourseRepository {
    var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getCourses(accessToken:String): Unit =
        withContext(Dispatchers.IO){
            var response= retrofit.fetCourses()

    }
}