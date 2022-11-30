package com.expert.multisound.module

import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides
    fun smsCallBack(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        var verificationCallbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
                override fun onVerificationFailed(e: FirebaseException) {}
                override fun onCodeSent(
                    verificationID: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {}
            }
        return verificationCallbacks
    }


}