package com.example.task.repository

import androidx.lifecycle.LiveData
import com.example.task.model.User
import com.example.task.data.UserDao
import com.example.task.model.UserWithRole

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<UserWithRole>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}