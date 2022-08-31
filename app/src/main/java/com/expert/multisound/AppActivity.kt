package com.expert.multisound

import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.expert.multisound.R
import com.expert.multisound.base.BaseActivity
import com.expert.multisound.module.AnimateStartModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class AppActivity : BaseActivity() {

    @Inject
    lateinit var animateS: AlphaAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.injectAppActivity(this)

        styleActivity()

    }

    fun styleActivity(){
        txt.startAnimation(animateS)
    }

}