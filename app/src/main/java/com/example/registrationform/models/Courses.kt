package com.example.registrationform.models

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName ("course_id") var courseId:String,
    @SerializedName ("course_name") var courseName:String,
    @SerializedName ("course_code") var courseCode:String,
    var desrciption:String,
    var instructor:String,


    )
