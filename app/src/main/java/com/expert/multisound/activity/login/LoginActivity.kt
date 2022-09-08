package com.expert.multisound.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.R
import com.expert.multisound.activity.registry.RegActivity
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

    fun onReg(view: View) {
        val intent = Intent(this, RegActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.outfade, R.anim.fade)
    }
    override fun onBackPressed() {}
}