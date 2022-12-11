package com.example.cocopeat_project


import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocopeat_project.R
import com.example.cocopeat_project.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel (val database: UserDao,
                          application: Application
    ) : AndroidViewModel(application) {

        val inputUsername = MutableLiveData<String>()

        val inputPassword = MutableLiveData<String>()

        private val _navigateToHome = MutableLiveData<Boolean>()

        val navigateToHome: LiveData<Boolean>
            get() = _navigateToHome

        @SuppressLint("NullSafeMutableLiveData")
        fun doneNavigating() {
            _navigateToHome.postValue(null)
        }

        fun loginButton() {
            if (inputUsername.value == null || inputPassword.value == null) {
                //R.id.username_edit_text.setError = "TEST"
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val usersNames = database.login(inputUsername.value!!)
                    if (usersNames != null) {
                        if(usersNames.password == inputPassword.value) {
                            inputUsername.postValue("")
                            inputPassword.postValue("")
                            _navigateToHome.postValue(true)
                        } else {
//                        error handling
                        }
                    } else {
//                    error handling
                    }

                }
            }
        }
    }