package com.example.festivalproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_password2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (register_password1.text.toString() != register_password2.text.toString()) {
                    register_passwordCheck.setText("#비밀번호가 일치하지 않습니다")
                } else if (register_password1.text.toString() == register_password2.text.toString()) {
                    register_passwordCheck.setText("비밀번호가 일치합니다")
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit", "beforeTextChanged: " + p0)
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("edit", "onTextChanged: " + p0)
            }
        })

        register_registerBtn.setOnClickListener {
            var isChecked = register_radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(isChecked)
            var userProfile: UserProfile = UserProfile(
                register_id.text.toString(),
                register_password1.text.toString(),
                radioButton.text.toString(),
                register_phoneNum.text.toString(),
                register_email.text.toString()
            )
            registerCompleteButton(userProfile,this@RegisterActivity)
        }
    }
}

fun registerCompleteButton(userProfile: UserProfile,activity: Activity) {
    Log.d(
        "registertest",
        "아이디" + userProfile.id + "비번" + userProfile.password + "성별" + userProfile.sex + "전화" + userProfile.phoneNum + "이메일" + userProfile.email
    )
    val intent = Intent(activity,LoginActivity::class.java)
    activity.startActivity(intent)
}

//db에 데이터 넣기
//추가기능 전화번호 인증