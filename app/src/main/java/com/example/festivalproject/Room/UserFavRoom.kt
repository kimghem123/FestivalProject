package com.example.festivalproject

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.festivalproject.Room.UserFavDao
import com.example.festivalproject.Room.UserFavEntity
import com.example.festivalproject.Room.UserProfileDao
import com.example.festivalproject.Room.UserProfileEntity
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types.newParameterizedType
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tickaroo.tikxml.Types.newParameterizedType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.sql.Types as Types

@Database(entities = [UserFavEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserFavDatabase : RoomDatabase(){
    abstract fun userFavDao(): UserFavDao

    companion object{
        private var instance:UserFavDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserFavDatabase?{
            if(instance == null){
                synchronized(UserFavDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserFavDatabase::class.java,
                        "userFavoList"
                    )
                        .build()
                }
            }
            return instance
        }
    }

}
/*class Converters {
    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)


}*/

class Converters {
    @TypeConverter
    fun fromList(value : MutableList<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<MutableList<String>>(value)
}