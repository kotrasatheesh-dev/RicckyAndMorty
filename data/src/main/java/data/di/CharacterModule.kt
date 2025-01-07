package data.di

import com.apollographql.apollo.ApolloClient
import data.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import domain.repository.CharactersRepository
import javax.inject.Singleton

@Module
object CharacterModule {
    @Provides
    @Singleton
    fun provideCharacterModule(apolloClient: ApolloClient): CharactersRepository {
        return CharactersRepositoryImpl(apolloClient)
    }
}
