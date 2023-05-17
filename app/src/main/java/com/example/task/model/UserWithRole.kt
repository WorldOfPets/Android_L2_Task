package com.example.task.model

import androidx.room.ColumnInfo
import androidx.room.Relation

data class UserWithRole(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var age: Int,
    @ColumnInfo(name = "role_id")
    var role: Int,
    @Relation(parentColumn = "role_id", entityColumn = "id", entity = Role::class)
    var roleName: Role
)
