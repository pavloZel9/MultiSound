package com.expert.multisound.activity.registry

import android.content.Context
import android.os.Bundle
import com.expert.multisound.R
import com.expert.multisound.base.DialogA

class DialogSmsCode(context: Context) : DialogA(context) {


    interface Update {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_sms)
        setCanceledOnTouchOutside(false)

    }

}