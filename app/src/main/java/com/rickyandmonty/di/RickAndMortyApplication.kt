package com.rickyandmonty.di

import android.app.Application
import com.rickyandmonty.di.component.DaggerAppComponent


class RickAndMortyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}
