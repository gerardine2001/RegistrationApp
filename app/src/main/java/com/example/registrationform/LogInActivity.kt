package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.registrationform.api.ApiClient
import com.example.registrationform.api.ApiInterface
import com.example.registrationform.databinding.ActivityLogInBinding
import com.example.registrationform.databinding.ActivityMainBinding
import com.example.registrationform.models.LoginRequest
import com.example.registrationform.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    lateinit var binding:ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                 var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
                var request=retrofit.loginStudent(lrgLogin)
                request.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                       if (response.isSuccessful)
                           Toast.makeText(baseContext,"Login successful",Toast.LENGTH_LONG).show()

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                        binding.prbLogIn.visibility=View.GONE

                    }

                })
                }
            }
        }
    }
}