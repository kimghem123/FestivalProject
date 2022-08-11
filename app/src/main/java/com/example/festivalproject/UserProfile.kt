package com.example.festivalproject

import java.io.Serializable

class UserProfile(
    var id:String? = null,
    var password:String? = null,
    var sex:String? = null,
    var phoneNum:String? = null,
    var email:String? = null
):Serializable