package com.example.festivalproject

data class UserProfile(
    var userId: String? = null,
    var userPassword: String? = null,
    var userNickName: String? = null,
    var userSex: String? = null,
    var userPhoneNum: String? = null,
    var userEmail: String? = null
) {
    fun setterUserId(id: String?){
        userId = id
    }
    fun setterPassword(password:String?){
        userPassword = password
    }
    fun setterSex(sex:String?) {
        userSex = sex
    }
    fun setterPhoneNum(phoneNum:String?){
        userPhoneNum = phoneNum
    }
    fun setterEmail(email:String?){
        userEmail = email
    }
    fun setterNickName(nickName:String?){
        userNickName = nickName
    }
    fun getterUserId(): String? {
        return userId
    }
    fun getterPassword(): String? {
        return userPassword
    }
    fun getterSex(): String? {
        return userSex
    }
    fun getterPhoneNum(): String? {
        return userPhoneNum
    }
    fun getterEmail(): String? {
        return userEmail
    }
    fun getterNickName(): String? {
        return userNickName
    }

}


