package com.expert.multisound

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.expert.multisound.activity.login.Main
import com.expert.multisound.base.BaseActivity
import kotlinx.android.synthetic.main.activity_app.*
import javax.inject.Inject


class AppActivity : BaseActivity() {

    @Inject
    lateinit var animateS: AlphaAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        (application as App).appComponent.injectAppActivity(this)

        styleActivity()

    }

    fun styleActivity(){
        animateS.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                Next()
            }
        })
        txt.startAnimation(animateS)
    }

    fun Next(){
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.outfade, R.anim.fade)
    }

}

