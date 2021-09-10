package com.example.registrationform.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registrationform.R
import com.example.registrationform.models.Courses

class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()
    }
    fun displayCourses(){
        var coursesList= listOf<Courses>(
            Courses("01","Android","AND 101Native android development","Native android development","John Owuor"),
            Courses("02","Python","PY 101Backend development with python","Backend development with python","James Mwai"),
            Courses("Javascript","Javascript","JS 101","Javascript for web development","PurityJ"),
            Courses("IOT","IOT 101","Internet of things","Barren","PurityJ")


        )
        rvCourses=findViewById(R.id.rvCourses)
        var coursesAdapter= CoursesAdapter(coursesList)
        rvCourses.layoutManager= LinearLayoutManager(baseContext)
        rvCourses.adapter=coursesAdapter

    }
}