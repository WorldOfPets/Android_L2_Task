package com.example.task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.task.data.UserDatabase
import com.example.task.model.Role
import com.example.task.model.User
import com.example.task.repository.RoleRepository
import com.example.task.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    //var readAllRole: LiveData<List<Role>>? = null
    var readRole: LiveData<Role>? = null
    private val repository: UserRepository
    private val roleRepository: RoleRepository

    init {
        val database = UserDatabase.getDatabase(application)
        val userDao = database.userDao()
        val roleDao = database.roleDao()
        repository = UserRepository(userDao)
        roleRepository = RoleRepository(roleDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
    fun getRole(roleName: String) {
        roleRepository.getRoleByName(roleName)
        readRole = roleRepository.readRole
        //readAllRole = roleRepository.readAllData
    }
    fun addRole(role: Role){
        viewModelScope.launch(Dispatchers.IO) {
            roleRepository.addRole(role)
        }
    }
}