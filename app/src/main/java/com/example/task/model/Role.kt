package com.example.task.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "role_table")
data class Role(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "roleName")
    var roleName: String
):Parcelable