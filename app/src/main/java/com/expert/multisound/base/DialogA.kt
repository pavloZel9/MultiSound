package com.expert.multisound.base

import android.app.Dialog
import android.content.Context

import android.view.WindowManager


open class DialogA(context: Context) : Dialog(context) {

    override fun setContentView(layoutResID: Int) {
        val window = window
        window!!.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.setContentView(layoutResID)
    }

}

