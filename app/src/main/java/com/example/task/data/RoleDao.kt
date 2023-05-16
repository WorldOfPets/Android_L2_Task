package com.example.task.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.task.model.Role

@Dao
interface RoleDao {
//    @Query("SELECT * FROM role_table WHERE roleName=':roleName' LIMIT 1")
//    fun getRoleByName(roleName: String): LiveData<List<Role>>

    @Query("SELECT * FROM role_table WHERE roleName=:roleName LIMIT 1")
    fun findRoleByName(roleName: String): LiveData<Role>

    @Query("INSERT INTO role_table (id, roleName) SELECT NULL, :roleName WHERE NOT EXISTS(SELECT * FROM role_table WHERE roleName=':roleName')")
    suspend fun addRole(roleName: String)
}