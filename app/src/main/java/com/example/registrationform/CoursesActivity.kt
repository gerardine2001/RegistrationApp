package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()
    }
    fun displayCourses(){
        var coursesList= listOf<Courses>(
            Courses("Android","AND 101","Native android development","John Owuor"),
            Courses("Python","PY 101","Backend development with python","James mwai"),
            Courses("Javascript","JS 101","Javascript for web development","Purity"),
            Courses("IOT","IOT 101","Internet of things","Barren")


        )
        rvCourses=findViewById(R.id.rvCourses)
        var coursesAdapter=CoursesAdapter(coursesList)
        rvCourses.layoutManager= LinearLayoutManager(baseContext)
        rvCourses.adapter=coursesAdapter

    }
}