package com.example.data.module

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object GraphQLClientModule {

    @Provides
    @Singleton
    fun  provideApolloClient(): ApolloClient {
        val apolloClient =
            ApolloClient
                .Builder()
                .serverUrl("https://rickandmortyapi.com/graphql")
                .build()
        return apolloClient

    }
}
