package com.example.festivalproject

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
            val test = LoginModel() //변수명 변경
            val userProfile = test.test(
                register_id.text.toString(),
                register_password1.text.toString(),
                radioButton.text.toString(),
                register_phoneNum.text.toString(),
                register_email.text.toString()
            )
            registerCompleteButton(this@RegisterActivity)
        }
    }

}

fun registerCompleteButton(activity: Activity) {
    val intent = Intent(activity, LoginActivity::class.java)
    activity.startActivity(intent)
}

//db에 데이터 넣기
//추가기능 전화번호 인증