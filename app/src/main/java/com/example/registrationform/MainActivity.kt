package com.example.registrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.registrationform.api.ApiClient
import com.example.registrationform.api.ApiInterface
import com.example.registrationform.databinding.ActivityMainBinding
import com.example.registrationform.models.RegistrationRequest
import com.example.registrationform.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSpinner()
    }

    fun setupSpinner() {
        var nationalities =
            arrayOf("select Nationality", "Kenyan", "Rwandan", "South Sudanese", "Ugandan")
        var nationalityadapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
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
                    nationality = nationality,
                    password = password
                )
                var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
                var request=retrofit.registerStudent(lrgRequest)
                request.enqueue(object : retrofit2.Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                      binding.pbRegistration.visibility=View.GONE
                        if (response.isSuccessful)
                            Toast.makeText(baseContext,"Registration Successfully",Toast.LENGTH_LONG).show()
                        else{
                            Toast.makeText(baseContext,response.errorBody()?.toString(),Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                     binding.pbRegistration.visibility=View.GONE
                     Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                    }

                })
            }
        }
    }
}