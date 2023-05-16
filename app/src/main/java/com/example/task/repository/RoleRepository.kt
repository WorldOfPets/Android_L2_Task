package com.example.task.repository

import androidx.lifecycle.LiveData
import com.example.task.data.RoleDao
import com.example.task.model.Role

class RoleRepository(private val roleDao: RoleDao) {
    //var readAllData: LiveData<List<Role>>? = null
    var readRole: LiveData<Role>? = null
    fun getRoleByName(roleName:String){
        //readAllData = roleDao.getRoleByName(roleName = roleName)
        readRole = roleDao.findRoleByName(roleName = roleName)
    }
    suspend fun addRole(user: Role){
        roleDao.addRole(user.roleName)
    }
//
//    suspend fun updateUser(user: Role){
//        roleDao.updateUser(user)
//    }
//    suspend fun deleteUser(user: Role){
//        roleDao.deleteUser(user)
//    }
//    suspend fun deleteAllUsers(){
//        roleDao.deleteAllUsers()
//    }
}