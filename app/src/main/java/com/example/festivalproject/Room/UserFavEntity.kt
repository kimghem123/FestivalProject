package com.example.festivalproject.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userFavoritList")
data class UserFavEntity(
    var userId: String? = null ,
    var favList: List<String>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
