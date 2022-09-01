package com.expert.multisound.activity.login

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStyle()
    }

    fun setStyle() {
        window.navigationBarColor = resources.getColor(R.color.color1)
        text_reg.setText(
            Html.fromHtml(
                 "Немає аккаунта? " + " <font color=\"#7E984C\"><b>" + "Створити новий" + "</b></font>"
            )
        )


    }
}