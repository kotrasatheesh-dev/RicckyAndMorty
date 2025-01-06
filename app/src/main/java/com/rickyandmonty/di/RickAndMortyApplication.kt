package com.rickyandmonty.di

import android.app.Application
import com.example.rickandmorty.data.di.component.DaggerAppComponent

class RickAndMortyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}
