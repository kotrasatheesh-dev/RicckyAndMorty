package com.example.data.module.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CharacterModule {
    @Provides
    @Singleton
    fun provideCharacterModule(apolloClient:ApolloClient):CharactersRepository{
        return CharactersRepositoryImpl(apolloClient)
    }
}
