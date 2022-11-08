package com.example.festivalproject.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userFavorite")
data class UserFavoriteEntity(
    var userId: String?,
    var favoriteList: List<String>?
){
    @PrimaryKey
    var id: Int = 0
}
