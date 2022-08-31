package com.expert.multisound.module

import android.view.animation.AlphaAnimation
import dagger.Module
import dagger.Provides
@Module
class AnimateStartModule {
    @Provides
    fun getAnimateStart(): AlphaAnimation {
        val animation1 = AlphaAnimation(0.0f, 1.0f)
        animation1.duration =2500
        animation1.startOffset = 500
        animation1.fillAfter = true
        return animation1
    }
}