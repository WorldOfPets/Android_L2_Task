package com.example.task.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task.model.Role
import com.example.task.model.User

@Database(entities = [User::class, Role::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun roleDao(): RoleDao
    abstract fun userDao(): UserDao


    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).createFromAsset("database/user_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}