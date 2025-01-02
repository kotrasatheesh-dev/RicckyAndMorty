package com.example.data.di

import com.apollographql.apollo.ApolloClient
import com.example.data.repository.CharactersRepositoryImpl
import com.example.domain.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton
@Module
object CharacterModule {
    @Provides
    @Singleton
    fun provideCharacterModule(apolloClient: ApolloClient): CharactersRepository {
        return CharactersRepositoryImpl(apolloClient)
    }
}