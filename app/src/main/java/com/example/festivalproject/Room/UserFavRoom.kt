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

@Database(entities = [UserFavEntity::class], version = 2)
@TypeConverters(
    value = [
        StringListTypeConverter::class
    ]
)
abstract class UserFavDatabase : RoomDatabase(){
    abstract fun userFavDao(): UserFavDao

    companion object{
        private var instance:UserFavDatabase? = null

        fun provideGson(): Gson {
            return Gson()
        }

        @Synchronized
        fun getInstance(context: Context,gson: Gson): UserFavDatabase?{
            if(instance == null){
                synchronized(UserFavDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserFavDatabase::class.java,
                        "userFavoritList"
                    )
                        .addTypeConverter(StringListTypeConverter(gson))
                        .build()
                }
            }
            return instance
        }

    }

}
@ProvidedTypeConverter
class StringListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: List<String>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String> {
        return gson.fromJson(value, Array<String>::class.java).toList()
    }
}
/*
class Converters {
    @TypeConverter
    fun fromList(value : MutableList<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<MutableList<String>>(value)
}*/
