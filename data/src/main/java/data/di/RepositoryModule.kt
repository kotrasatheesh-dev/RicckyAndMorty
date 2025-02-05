package data.di

import dagger.Binds
import dagger.Module
import javax.inject.Singleton
import domain.repository.EpisodeDetailsRepository
import data.repository.EpisodeDetailsRepositoryImpl

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEpisodeDetailsRepository(
        impl: EpisodeDetailsRepositoryImpl
    ): EpisodeDetailsRepository
}
