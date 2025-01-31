package data.di

import com.apollographql.apollo.ApolloClient
import data.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import data.repository.CharacterDetailsRepositoryImpl
import domain.repository.CharacterDetailsRepository
import domain.repository.CharactersRepository
import javax.inject.Singleton

@Module
object CharacterModule {
    @Provides
    @Singleton
    fun provideCharactersRepository(apolloClient: ApolloClient): CharactersRepository {
        return CharactersRepositoryImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsRepository(apolloClient: ApolloClient): CharacterDetailsRepository {
        return CharacterDetailsRepositoryImpl(apolloClient)
    }
}
