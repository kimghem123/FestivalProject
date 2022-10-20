package com.example.festivalproject.RegisterPackage

import android.app.Activity
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
import com.example.festivalproject.Room.UserProfileEntity
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import kotlinx.android.synthetic.main.activity_register.*
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
            } else if (register_password1.text.toString() == register_password2.text.toString()) {
                register_passwordCheck.setText("비밀번호가 일치합니다")
            }
        }

        register_email.doAfterTextChanged {
            if (register_id.text.toString().isNotEmpty()
                && register_password1.text.toString().isNotEmpty()
                && register_password2.text.toString().isNotEmpty()
                && register_password1.text.toString() == register_password2.text.toString()
                && register_phoneNum.text.toString().isNotEmpty()
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
            val registerModel: RegisterModel = RegisterModel() //변수명 변경

            register_registerBtn.showProgress {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            val newUser = UserProfileEntity(
                register_id.text.toString(),
                register_password1.text.toString(),
                radioButton.text.toString(),
                register_phoneNum.text.toString(),
                register_email.text.toString()
            )
            registerModel.signUpNewUser(
                newUser,
                this
            )

            Handler(Looper.getMainLooper()).postDelayed(
                { register_registerBtn.hideProgress(R.string.success) },
                2000
            )

            //userProfileModel.checkUser(this)

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

//db에 데이터 넣기
//추가기능 전화번호 인증