package com.expert.multisound.activity.registry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.App
import com.expert.multisound.R
import com.expert.multisound.activity.login.LoginActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_reg.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RegActivity : AppCompatActivity() {

    @Inject
    lateinit var smsCallBack:  PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        (application as App).appComponent.injectRegActivity(this)


    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.outfade, R.anim.fade)
    }

    fun onReg(view: View) {

        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions
                .newBuilder(FirebaseAuth.getInstance())
                .setActivity(this)
                .setPhoneNumber(ccp.fullNumber)
                .setTimeout(10L, TimeUnit.SECONDS)
                .setCallbacks(smsCallBack)
                .build())

        val dialogSms = DialogSmsCode(this)
        dialogSms.show()
    }

}