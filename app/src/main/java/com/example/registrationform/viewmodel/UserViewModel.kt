package com.example.registrationform.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationform.models.LoginRequest
import com.example.registrationform.models.LoginResponse
import com.example.registrationform.models.RegistrationRequest
import com.example.registrationform.models.RegistrationResponse
import com.example.registrationform.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val loginLiveData= MutableLiveData<LoginResponse>()
    val registrationLiveData = MutableLiveData<RegistrationResponse>()
    val errorLiveData = MutableLiveData<String>()


    fun registerStudent(registrationRequest: RegistrationRequest) {
        viewModelScope.launch {
            var response = userRepository.registerStudent(registrationRequest)
            if (response.isSuccessful) {
                registrationLiveData.postValue(response.body())

            } else {
                errorLiveData.postValue(response.errorBody()?.toString())
            }

        }
    }


}


