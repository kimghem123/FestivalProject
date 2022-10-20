package com.example.festivalproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.festivalproject.Room.UserProfileDao
import com.example.festivalproject.Room.UserProfileEntity

@Database(entities = [UserProfileEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserProfileDao

    companion object{
        private var instance:UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase?{
            if(instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "userProfile"
                    ).build()
                }
            }
            return instance
        }
    }
}