package com.rickyandmonty.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.common.module.ApolloClientModule
import com.example.data.di.CharacterModule
import com.rickyandmonty.di.MainActivity
import com.rickyandmonty.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CharacterModule::class, ViewModelModule::class, ApolloClientModule::class])
interface CoreComponent {
    fun inject(activity: MainActivity)

    fun viewModelFactory(): ViewModelProvider.Factory // Expose ViewModelFactory
}
