package com.example.registrationform.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registrationform.Constants
import com.example.registrationform.databinding.ActivityLogInBinding
import com.example.registrationform.models.LoginRequest
import com.example.registrationform.viewmodel.LoginViewModel
import com.example.registrationform.viewmodel.UserViewModel


class LogInActivity : AppCompatActivity() {
    lateinit var binding:ActivityLogInBinding
    lateinit var sharedPrefs: SharedPreferences
    val loginViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences(Constants.REGISTRATION_PREFS,Context.MODE_PRIVATE)
        clickLogin()

    }

    override fun onResume() {
        super.onResume()
        loginViewModel.loginLiveData.observe(this) { loginResponse ->
            var accessToken= loginResponse.accessToken
            var editor=sharedPrefs.edit()
            editor.putString(Constants.ACCESS_TOKEN,accessToken)
            editor.putString(Constants.STUDENT_ID,loginResponse.studentId)
            editor.apply()
            if (loginResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Login successful", Toast.LENGTH_LONG).show()
            }
             
            binding.prbLogIn.visibility = View.GONE
        }
        loginViewModel.errorLiveData.observe(this, { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            binding.prbLogIn.visibility=View.GONE
        })
    }

    fun clickLogin(){
        var error=false
        binding.btnLogin1.setOnClickListener {
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()){
                error = true
                binding.etEmail.setError("This field is required")
            }
            var password=binding.etPassword2.text.toString()
            if (password.isEmpty()){
                error=true
                binding.etPassword2.setError("This field is required")
            }
            if (!error){
                binding.prbLogIn.visibility=View.GONE
                var lrgLogin=LoginRequest(
                    email=email,
                    password=password
                )


            }
        }
    }
}
