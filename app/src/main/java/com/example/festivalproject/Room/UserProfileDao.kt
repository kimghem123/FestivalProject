package com.example.festivalproject.Room

import androidx.room.*
import com.example.festivalproject.FindUserIdPackage.DetailFindUserIdFragment

@Dao
interface UserProfileDao {

    @Insert
    suspend fun insert(user: UserProfileEntity)

    @Query("DELETE FROM userProfile")
    suspend fun deleteAll()

    @Query("SELECT * FROM userProfile")
    suspend fun getAll(): List<UserProfileEntity>

    @Query("SELECT userPassword FROM userProfile WHERE userId = :id")
    suspend fun login(id:String?): String?

    @Query("SELECT userNickName FROM userProfile WHERE userID = :id")
    suspend fun getNickName(id:String):String?

    @Query("SELECT userId FROM userProfile WHERE userNickName =:name AND userPhoneNum =:phoneNum")
    suspend fun findUserId(name: String,phoneNum:String):String?

    @Query("UPDATE userProfile SET userPassword = :password WHERE userId = :id ")
    suspend fun changePassword(password:String,id:String)

}