package com.example.festivalproject.Room

import androidx.room.*

@Dao
interface UserProfileDao {

    @Insert
    suspend fun insert(user: UserProfileEntity)

    @Query("DELETE FROM userProfile")
    suspend fun deleteAll()

    @Query("SELECT * FROM userProfile")
    suspend fun getAll(): List<UserProfileEntity>

    @Query("SELECT userPassword FROM userProfile WHERE userId = :id")
    suspend fun login(id:String): String?

}