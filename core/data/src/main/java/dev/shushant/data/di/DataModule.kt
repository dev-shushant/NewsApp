package dev.shushant.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shushant.data.repository.NewsRepository
import dev.shushant.data.repository.NewsRepositoryImpl
import dev.shushant.data.utils.AppDispatcher
import dev.shushant.data.utils.AppDispatcherImpl
import dev.shushant.data.utils.ConnectivityManagerNetworkMonitor
import dev.shushant.data.utils.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNewsRepository(
        newsRepository: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

    @Binds
    fun bindsDispatcher(appDispatcherImpl: AppDispatcherImpl): AppDispatcher
}