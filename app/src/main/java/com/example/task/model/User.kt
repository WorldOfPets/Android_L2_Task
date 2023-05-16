package com.example.task.model


import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table", foreignKeys = arrayOf(ForeignKey(
    entity=Role::class,
    parentColumns= arrayOf("id"),
    childColumns = arrayOf("role_id"),
    onDelete=ForeignKey.CASCADE)))
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    @ColumnInfo(name = "role_id")
    val role: Int,
): Parcelable
