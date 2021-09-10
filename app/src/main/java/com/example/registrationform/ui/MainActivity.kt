package com.example.registrationform.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registrationform.Constants
import com.example.registrationform.R
import com.example.registrationform.api.ApiClient
import com.example.registrationform.api.ApiInterface
import com.example.registrationform.databinding.ActivityMainBinding
import com.example.registrationform.models.RegistrationRequest
import com.example.registrationform.models.RegistrationResponse
import com.example.registrationform.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var  sharedPrefs: SharedPreferences
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        sharedPrefs =getSharedPreferences(Constants.REGISTRATION_PREFS, Context.MODE_PRIVATE)
        redirectUser()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registrationLiveData.observe(this ,{regResponse->
            
        })

    }


    fun setupSpinner() {
        var nationalities = arrayOf("select Nationality", "Kenyan", "Rwandan", "South Sudanese", "Ugandan")
        var nationalityAdapter = ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityAdapter
        clickRegister()
    }

    fun clickRegister() {
        var error = false
        binding.btnRegister.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field required")
            }
            var dob = binding.etDate.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDate.setError("this field required")
            }
            var nationality = ""
            if (binding.spNationality.selectedItemPosition != 0) {
                nationality = binding.spNationality.selectedItem.toString()
            } else {
                error = true
                Toast.makeText(baseContext, "select Nationality", Toast.LENGTH_LONG).show()
            }
            var phoneNumber = binding.etPhone.text.toString()
            if (phoneNumber.isEmpty()) {
                error = true
                binding.etPhone.setError("this field required")
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.setError("this field required")
            }
            var password = binding.etPassword.text.toString()
            if (password.isEmpty()) {
                error = true
                binding.etPassword.setError("this field required")
            }
            if (!error) {
                binding.pbRegistration.visibility = View.VISIBLE
                var lrgRequest = RegistrationRequest(
                    name = name,
                    PhoneNumber = phoneNumber,
                    email = email,
                    dateOfBirth = dob,
                    nationality = nationality.uppercase(),
                    password = password
                )
                userViewModel.registerStudent(lrgRequest)

            }
        }
    }
    fun redirectUser(){
        var accessToken=sharedPrefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY)
        if (accessToken!!.isNotEmpty() && accessToken.isBlank()) {
            startActivity(Intent( this, CoursesActivity::class.java))



        }
        else{
            startActivity(Intent(this, LogInActivity::class.java))

        }
    }

}