package com.expert.multisound.activity

import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.expert.multisound.R
import com.expert.multisound.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        styleActivity()
    }
    fun styleActivity(){
        val animation1 = AlphaAnimation(0.0f, 1.0f)
        animation1.duration =2500
        animation1.startOffset = 500
        animation1.fillAfter = true
        txt.startAnimation(animation1)
    }

}