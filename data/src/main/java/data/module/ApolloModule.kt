package data.module

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApolloModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        val apolloClient =
            ApolloClient
                .Builder()
                .serverUrl(Constants.BASE_URL)
                .build()
        return apolloClient
    }
}
