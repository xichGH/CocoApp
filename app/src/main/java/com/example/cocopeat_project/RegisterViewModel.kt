package com.example.cocopeat_project

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocopeat_project.User
import com.example.cocopeat_project.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(val database: UserDao,
                        application: Application
) : AndroidViewModel(application) {

    val inputFirstName = MutableLiveData<String>()

    val inputLastName = MutableLiveData<String>()

    val inputUsername = MutableLiveData<String>()

    val inputPassword = MutableLiveData<String>()

    val _navigateToLogin = MutableLiveData<Boolean>()

    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    @SuppressLint("NullSafeMutableLiveData")
    fun doneNavigating() {
        _navigateToLogin.postValue(null)
    }

    fun registerButton() {
        if (inputFirstName.value == null || inputLastName.value == null ||inputUsername.value == null || inputPassword.value == null) {
//            _errorToast.value = true
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val usersNames = database.login(inputUsername.value!!)
                if (usersNames != null) {
//                    _errorToastUsername.value = true
                } else {
                    val firstname = inputFirstName.value!!
                    val lastname = inputLastName.value!!
                    val username = inputUsername.value!!
                    val password = inputPassword.value!!
                    database.register(User(0, firstname, lastname, username, password))
                    inputFirstName.postValue("")
                    inputLastName.postValue("")
                    inputUsername.postValue("")
                    inputPassword.postValue("")
                    _navigateToLogin.postValue(true)
                }

            }
        }

    }

}
