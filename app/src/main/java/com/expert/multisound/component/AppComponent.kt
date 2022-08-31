package com.expert.multisound.component

import com.expert.multisound.AppActivity
import com.expert.multisound.module.AnimateStartModule
import dagger.Component

@Component(modules=[AnimateStartModule::class])
interface AppComponent {
   // fun AnimateStart():AnimateStartModule
    fun injectAppActivity(appActivity: AppActivity)
}