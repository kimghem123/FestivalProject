package com.example.festivalproject.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userFavoList")
data class UserFavEntity(
    var userId: String? = null ,
    var favList: MutableList<String>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
