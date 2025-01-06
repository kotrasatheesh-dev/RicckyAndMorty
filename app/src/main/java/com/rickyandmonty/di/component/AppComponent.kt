package com.rickyandmonty.di.component

import com.example.common.module.ApolloModule
import com.example.data.di.CharacterModule
import com.rickyandmonty.di.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CharacterModule::class, ApolloModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
