package com.example.r411.ui.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun login(mail:String, password:String) {

        if(password.length < 6) {
            throw Exception("Password must be at least 6 characters")
        }



    }

}