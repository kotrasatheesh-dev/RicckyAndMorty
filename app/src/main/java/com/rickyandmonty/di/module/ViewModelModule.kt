package com.example.rickandmorty.data.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.viewmodel.CharactersViewModel
import com.example.rickandmorty.data.di.viewmodel.ViewModelFactoryProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindMyViewModel(myViewModel: CharactersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryProvider): ViewModelProvider.Factory
}
