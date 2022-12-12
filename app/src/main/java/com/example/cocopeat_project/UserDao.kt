package com.example.cocopeat_project

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(register: User)

    @Query("SELECT * FROM new_user_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM new_user_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM new_user_table WHERE username LIKE :username")
    suspend fun getUsername(username: String): User?

}