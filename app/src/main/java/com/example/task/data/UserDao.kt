package com.example.task.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllData():LiveData<List<User>>
}