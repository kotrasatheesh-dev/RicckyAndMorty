package com.rickyandmonty.ui

import android.app.Application
import com.rickyandmonty.ui.component.DaggerAppComponent

class RMApplication : Application(){
    val appComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}
