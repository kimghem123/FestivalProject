package com.example.festivalproject.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userProfile")
data class UserProfileEntity(
    var userId: String?,
    var userPassword: String?,
    var userNickName:String?,
    var userSex: String?,
    var userPhoneNum: String?,
    var userEmail: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
