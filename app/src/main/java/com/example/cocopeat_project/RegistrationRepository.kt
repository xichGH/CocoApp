package com.example.cocopeat_project

import android.util.Log
import androidx.lifecycle.LiveData

class RegistrationRepository(private val dao: UserDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getUserName(username: String):User?{
        return dao.getUsername(username)
    }
    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}
