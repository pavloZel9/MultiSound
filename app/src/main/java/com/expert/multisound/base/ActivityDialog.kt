package com.expert.multisound.base

import android.util.DisplayMetrics
import android.view.WindowManager

abstract class ActivityDialog : BaseActivity() {
    /**
     * Расчет минимальной ширины диалога от размера экрана
     * @return int windowMinWidthMajor
     */
    private fun windowMinWidthMajor(persent: Int): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        //        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        return width * persent / 100
    }

    override fun setContentView(layoutResID: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.setContentView(layoutResID)
        val minWidthPercents = 80
        window.setLayout(
            windowMinWidthMajor(minWidthPercents),
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
