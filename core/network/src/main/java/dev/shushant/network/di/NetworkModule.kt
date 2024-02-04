package dev.shushant.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shushant.network.source.NetworkDataSource
import dev.shushant.network.source.NetworkDataSourceImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkDataSource(
    ): NetworkDataSource =
        NetworkDataSourceImpl()
}