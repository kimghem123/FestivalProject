package com.example.festivalproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.festivalproject.Room.UserProfileDao
import com.example.festivalproject.Room.UserProfileEntity

@Database(entities = [UserProfileEntity::class], version = 2)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userProfileDao(): UserProfileDao

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
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return instance
        }
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userProfile ADD COLUMN userNickName TEXT")
            }
        }
    }

}

