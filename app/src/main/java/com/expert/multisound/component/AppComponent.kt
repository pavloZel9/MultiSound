package com.expert.multisound.component

import com.expert.multisound.AppActivity
import com.expert.multisound.module.AnimateModule
import dagger.Component

@Component(modules=[AnimateModule::class])
interface AppComponent {
   // fun AnimateStart():AnimateStartModule
    fun injectAppActivity(appActivity: AppActivity)
}