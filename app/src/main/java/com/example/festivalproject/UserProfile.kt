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

    /*fun signUpNewUser(
        context: Context
    ) {
        val userProfileEntity = UserProfileEntity(
            userId,
            userPassword,
            userNickName,
            userSex,
            userPhoneNum,
            userEmail
        )
        val db = UserDatabase.getInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            db!!.userProfileDao().insert(userProfileEntity)
        }

        val userFavoriteEntity = UserFavoriteEntity(
            userId,
            mutableListOf<String>()
        )
        val db2 = UserFavorite.getInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            db2!!.userFavoriteDao().insert(userFavoriteEntity)
        }
    }

    suspend fun loginCheck(context: Context): Boolean {
        val db = UserDatabase.getInstance(context.applicationContext)
        val checkPassword = db!!.userProfileDao().login(userId).toString()
        if (userPassword.equals(checkPassword)) return true else return false
    }

    fun autoLogin(flag: Boolean, context: Context) {
        val sp = context.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("userId", userId)
        editor.putBoolean("flag", flag)
        editor.commit()
    }*/
}


