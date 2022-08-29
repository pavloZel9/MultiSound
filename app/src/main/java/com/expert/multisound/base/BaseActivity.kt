package com.expert.multisound.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.R
import android.os.Build




abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle()
    }
    fun setStyle() {
        window.navigationBarColor = resources.getColor(R.color.main_bckg)
    }

}