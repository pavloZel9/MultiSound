package com.expert.multisound

import android.app.Application
import com.expert.multisound.component.DaggerAppComponent

class App: Application() {

    val appComponent = DaggerAppComponent.create()

}