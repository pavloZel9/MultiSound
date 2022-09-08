package com.expert.multisound.module

import android.app.Activity
import android.content.Context
import android.view.animation.AlphaAnimation
import android.widget.Toast
import com.expert.multisound.activity.registry.DialogSmsCode
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_reg.*
import java.util.concurrent.TimeUnit

@Module
class AuthModule {
    @Provides
    fun smsCallBack(ctx: Context): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        var verificationCallbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
                override fun onVerificationFailed(e: FirebaseException) {
                    if (e is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(ctx, e.toString(), Toast.LENGTH_LONG)
                    } else if (e is FirebaseTooManyRequestsException) {
                        Toast.makeText(ctx, e.toString(), Toast.LENGTH_LONG)
                    }
                }

                override fun onCodeSent(
                    verificationID: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {

                }
            }
        return verificationCallbacks
    }



}