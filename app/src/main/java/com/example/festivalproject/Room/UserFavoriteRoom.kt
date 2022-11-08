package com.example.festivalproject.Room

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.festivalproject.UserDatabase
import com.google.gson.Gson

@Database(entities = [UserFavoriteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserFavorite:RoomDatabase(){
    abstract fun userFavoriteDao():UserFavoriteDao

    companion object{
        private var instance: UserFavorite? = null

        @Synchronized
        fun getInstance(context: Context): UserFavorite?{
            if(instance == null){
                synchronized(UserFavorite::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserFavorite::class.java,
                        "userFavorite"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}