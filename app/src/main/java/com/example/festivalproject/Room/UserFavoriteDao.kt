package com.example.festivalproject.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserFavoriteDao {
    @Insert
    suspend fun insert(userFavorite: UserFavoriteEntity)

    @Query("UPDATE userFavorite SET favoriteList =:favoriteList WHERE userId = :id")
    suspend fun addFavorite(favoriteList:List<String>?,id: String?)

    @Query("SELECT favoriteList FROM userFavorite WHERE userId = :id")
    suspend fun getFavorite(id:String?): List<String>?

}