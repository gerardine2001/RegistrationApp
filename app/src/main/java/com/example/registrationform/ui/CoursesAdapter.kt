package com.example.registrationform.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registrationform.R
import com.example.registrationform.models.Courses

class CoursesAdapter(var courseList: List<Courses>):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var itemView=LayoutInflater.from(parent.context).inflate(R.layout.courses_list_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var currentCourses=courseList.get(position)
       holder.tvCourseName.text=currentCourses.courseName
        holder.tvCourseCode.text=currentCourses.courseCode
        holder.tvDescription.text=currentCourses.desrciption
        holder.tvInsrtuctor.text=currentCourses.instructor

    }

    override fun getItemCount(): Int {
        return courseList.size
    }

}
class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvCourseName=itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvCourseCode=itemView.findViewById<TextView>(R.id.tvCode)
    var tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInsrtuctor=itemView.findViewById<TextView>(R.id.tvInstractor)
}


