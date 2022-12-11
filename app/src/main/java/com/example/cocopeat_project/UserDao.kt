package com.example.cocopeat_project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun register(user: User)

    @Query("SELECT * FROM user_table WHERE username LIKE :username")
    fun login(username: String): User

}