package com.example.festivalproject.RegisterPackage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RadioButton
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.festivalproject.LoginPackage.LoginActivity
import com.example.festivalproject.R
import com.example.festivalproject.Room.UserFavEntity
import com.example.festivalproject.UserProfile
import com.example.festivalproject.Room.UserProfileEntity
import com.example.festivalproject.UserDatabase
import com.example.festivalproject.UserFavDatabase
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class RegisterActivity : AppCompatActivity() {
    lateinit var list: ArrayList<UserProfileEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bindProgressButton(register_registerBtn)
        register_registerBtn.attachTextChangeAnimator()

        register_password2.doOnTextChanged { text, start, before, count ->
            if (register_password1.text.toString() != register_password2.text.toString()) {
                register_passwordCheck.setText("#비밀번호가 일치하지 않습니다")
                register_passwordCheck.setTextColor(Color.RED)
            } else if (register_password1.text.toString() == register_password2.text.toString()) {
                register_passwordCheck.setText("비밀번호가 일치합니다")
                register_passwordCheck.setTextColor(Color.BLUE)
            }
        }

        register_email.doAfterTextChanged {
            if (register_id.text.toString().isNotEmpty()
                && register_password1.text.toString().isNotEmpty()
                && register_password2.text.toString().isNotEmpty()
                && register_password1.text.toString() == register_password2.text.toString()
                && register_phoneNum.text.toString().isNotEmpty()
                && register_nickName.text.toString().isNotEmpty()
                && register_email.text.toString().isNotEmpty()
            ) {
                Log.d("register", "check")
                register_registerBtn.isEnabled = true
                register_registerBtn.setBackgroundColor(Color.parseColor("#FFBF87"))

            } else {
                Log.d("register", "false")
                register_registerBtn.isEnabled = false
            }
        }

        register_registerBtn.setOnClickListener {
            var isChecked = register_radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(isChecked)

            register_registerBtn.showProgress {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            val newUser = UserProfile()
                .apply {
                setterUserId(register_id.text.toString())
                setterPassword(register_password1.text.toString())
                setterNickName(register_nickName.text.toString())
                setterSex(radioButton.text.toString())
                setterPhoneNum(register_phoneNum.text.toString())
                setterEmail(register_email.text.toString())
            }

            signUpNewUser(
                newUser,this
            )

            Handler(Looper.getMainLooper()).postDelayed(
                { register_registerBtn.hideProgress(R.string.success) },
                2000
            )

            Handler(Looper.getMainLooper()).postDelayed(
                { registerCompleteButton(this@RegisterActivity) },
                3000
            )
        }
    }

}

fun registerCompleteButton(activity: Activity) {

    val intent = Intent(activity, LoginActivity::class.java)
    activity.startActivity(intent)
    activity.finish()
}

fun signUpNewUser(
    user: UserProfile,
    context: Context
) {
    val userProfileEntity = UserProfileEntity(
        user.getterUserId(),
        user.getterPassword(),
        user.getterNickName(),
        user.getterSex(),
        user.getterPhoneNum(),
        user.getterEmail()
    )

    val list = listOf<String>()
    val userFavEntity = UserFavEntity(user.getterUserId(),list)

    val db = UserDatabase.getInstance(context.applicationContext)
    val db2 = UserFavDatabase.getInstance(context.applicationContext, gson = Gson())
    CoroutineScope(Dispatchers.IO).launch {
        db!!.userProfileDao().insert(userProfileEntity)
        db2!!.userFavDao().insert(userFavEntity)
    }
}
