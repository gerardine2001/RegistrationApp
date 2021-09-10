package com.example.registrationform.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationform.models.LoginRequest
import com.example.registrationform.models.LoginResponse
import com.example.registrationform.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val userRepository= UserRepository()
    var loginLiveData= MutableLiveData<LoginResponse>()
    var errorLiveData= MutableLiveData<String>()



    fun loginStudent(loginRequest: LoginRequest){
        viewModelScope.launch {
            var response=userRepository.loginStudent(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.body()?.toString())
            }

        }

    }


}