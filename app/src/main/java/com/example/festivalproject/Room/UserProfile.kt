package com.example.festivalproject.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class UserProfile(
    var userId:String? = null,
    var userPassword:String? = null,
    var userSex:String? = null,
    var userPhoneNum:String? = null,
    var userEmail:String? = null
):Serializable
