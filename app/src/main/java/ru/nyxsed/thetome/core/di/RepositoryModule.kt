package ru.nyxsed.thetome.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nyxsed.thetome.core.data.repository.GameStateRepositoryImpl
import ru.nyxsed.thetome.core.domain.repository.GameStateRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGameStateRepository(
        impl : GameStateRepositoryImpl
    ) : GameStateRepository
}