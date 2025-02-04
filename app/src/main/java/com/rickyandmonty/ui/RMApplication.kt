    package com.rickyandmonty.ui

    import android.app.Application
    import com.rickyandmonty.ui.component.AppComponent
    import com.rickyandmonty.ui.component.DaggerAppComponent

    class RMApplication : Application() {
        val appComponent: AppComponent by lazy {
            DaggerAppComponent.create()
        }
    }
