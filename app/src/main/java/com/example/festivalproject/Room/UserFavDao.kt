package com.example.festivalproject.Room

import androidx.room.*

@Dao
interface UserFavDao {

    @Insert
    suspend fun insert(userFav: UserFavEntity)

   @Query("SELECT favList FROM userFavoritList WHERE userID = :id")
    suspend fun getfavList(id:String):String

    @Query("UPDATE userFavoritList SET favList = :list WHERE userId = :id ")
    suspend fun setfavList(list:List<String>,id:String)
}