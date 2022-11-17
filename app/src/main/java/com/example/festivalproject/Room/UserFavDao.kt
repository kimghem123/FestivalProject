package com.example.festivalproject.Room

import androidx.room.*

@Dao
interface UserFavDao {

    @Insert
    suspend fun insert(userFav: UserFavEntity)

   @Query("SELECT favList FROM userFavoList WHERE userID = :id")
    suspend fun getfavList(id:String):MutableList<String>

    @Query("UPDATE userFavoList SET favList = :list WHERE userId = :id ")
    suspend fun setfavList(list:MutableList<String>,id:String)
}