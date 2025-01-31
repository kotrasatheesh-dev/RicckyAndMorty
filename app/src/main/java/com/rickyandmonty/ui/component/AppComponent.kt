package com.rickyandmonty.ui.component

import androidx.lifecycle.ViewModelProvider
import com.rickyandmonty.ui.module.ViewModelModule
import com.rickyandmonty.ui.MainActivity
import dagger.Component
import data.di.CharacterModule
import data.module.ApolloModule
import javax.inject.Singleton

@Singleton
@Component(modules = [CharacterModule::class, ViewModelModule::class, ApolloModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    fun viewModelFactory(): ViewModelProvider.Factory
}
